package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieGenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.GenreDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieGenre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieGenreId;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.GenreMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.MovieMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.FiltroGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/genres")
@PreAuthorize("hasRole('ADMIN')")
public class GenreController {

    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;
    private final MovieGenreRepository movieGenreRepository;

    @Autowired
    public GenreController(GenreRepository genreRepository,
                           MovieRepository movieRepository,
                           MovieGenreRepository movieGenreRepository) {
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
        this.movieGenreRepository = movieGenreRepository;
    }

    @GetMapping("/")
    public String doListar(Model model, @ModelAttribute("filtro") FiltroGenre filtro) {
        List<GenreDTO> genres;

        if (filtro == null || filtro.getNombre() == null || filtro.getNombre().isEmpty()) {
            genres = genreRepository.findAll().stream()
                    .map(GenreMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            genres = genreRepository.findByNameContainingIgnoreCase(filtro.getNombre()).stream()
                    .map(GenreMapper::toDto)
                    .collect(Collectors.toList());
        }

        model.addAttribute("genres", genres);
        model.addAttribute("filtro", filtro == null ? new FiltroGenre() : filtro);
        return "genre";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") FiltroGenre filtro, Model model) {
        return doListar(model, filtro);
    }

    @GetMapping("/edit")
    public String doEditar(@RequestParam(value = "id", required = false) Integer id, Model model) {
        GenreDTO dto;

        if (id != null) {
            dto = genreRepository.findById(id)
                    .map(GenreMapper::toDto)
                    .orElse(new GenreDTO());
        } else {
            dto = new GenreDTO();
        }

        // Para gestión de películas
        List<MovieDTO> relatedMovies;
        List<MovieDTO> unrelatedMovies = new ArrayList<>();

        if (id != null) {
            // Películas relacionadas a través de MovieGenre
            relatedMovies = movieGenreRepository.findByGenreId(id).stream()
                    .map(MovieGenre::getMovie)
                    .map(MovieMapper::toDTO)
                    .collect(Collectors.toList());

            // Todas las películas menos las relacionadas
            unrelatedMovies = movieRepository.findAll().stream()
                    .filter(movie -> relatedMovies.stream()
                            .noneMatch(rm -> rm.getId().equals(movie.getId())))
                    .map(MovieMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            relatedMovies = new ArrayList<>();
            unrelatedMovies = movieRepository.findAll().stream()
                    .map(MovieMapper::toDTO)
                    .collect(Collectors.toList());
        }

        model.addAttribute("genreForm", dto);
        model.addAttribute("relatedMovies", relatedMovies);
        model.addAttribute("unrelatedMovies", unrelatedMovies);
        return "editGenre";
    }

    @PostMapping("/save")
    public String doGuardar(@ModelAttribute("genreForm") GenreDTO dto, Model model) {
        // Validación de nombre único
        Genre existing = genreRepository.findByNameIgnoreCase(dto.getName());
        if (existing != null && (dto.getId() == null || !existing.getId().equals(dto.getId()))) {
            model.addAttribute("error", "Ya existe un género con ese nombre");
            return "editGenre";
        }

        Genre genre = GenreMapper.toEntity(dto);
        genreRepository.save(genre);
        return "redirect:/genres/";
    }

    @PostMapping("/delete")
    public String doBorrar(@RequestParam("id") Integer id) {
        // Eliminar todas las relaciones MovieGenre primero
        List<MovieGenre> relaciones = movieGenreRepository.findByGenreId(id);
        movieGenreRepository.deleteAll(relaciones);

        // Luego eliminar el género
        genreRepository.deleteById(id);
        return "redirect:/genres/";
    }

    @PostMapping("/associateMovies")
    public String associateMovies(@ModelAttribute("genreForm") GenreDTO dto) {
        if (dto.getId() != null && dto.getSelectedMovieIds() != null && !dto.getSelectedMovieIds().isEmpty()) {
            Genre genre = genreRepository.findById(dto.getId()).orElse(null);
            if (genre != null) {
                for (Integer movieId : dto.getSelectedMovieIds()) {
                    Movie movie = movieRepository.findById(movieId).orElse(null);
                    if (movie != null) {
                        // Crear y guardar nueva relación
                        MovieGenreId id = new MovieGenreId();
                        id.setMovieId(movieId);
                        id.setGenreId(dto.getId());

                        MovieGenre relacion = new MovieGenre();
                        relacion.setId(id);
                        relacion.setMovie(movie);
                        relacion.setGenre(genre);

                        movieGenreRepository.save(relacion);
                    }
                }
            }
        }
        return "redirect:/genres/edit?id=" + dto.getId();
    }

    @GetMapping("/removeMovie")
    public String removeMovieFromGenre(@RequestParam("genreId") Integer genreId,
                                       @RequestParam("movieId") Integer movieId) {
        // Buscar y eliminar la relación específica
        MovieGenreId id = new MovieGenreId();
        id.setMovieId(movieId);
        id.setGenreId(genreId);

        movieGenreRepository.deleteById(id);
        return "redirect:/genres/edit?id=" + genreId;
    }
}