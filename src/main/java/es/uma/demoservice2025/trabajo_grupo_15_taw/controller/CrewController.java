package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.CrewRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.CrewDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Crew;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.CrewMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.MovieMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.FiltroCrew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/crew")
@PreAuthorize("hasRole('ADMIN')")
public class CrewController {

    private final CrewRepository crewRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public CrewController(CrewRepository crewRepository, MovieRepository movieRepository) {
        this.crewRepository = crewRepository;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/")
    public String doListar(Model model, @ModelAttribute("filtro") FiltroCrew filtro) {
        List<CrewDTO> crewList;

        if (filtro == null || filtro.getNombre() == null || filtro.getNombre().isEmpty()) {
            crewList = crewRepository.findAll().stream()
                    .map(CrewMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            crewList = crewRepository.findByNameContainingIgnoreCase(filtro.getNombre()).stream()
                    .map(CrewMapper::toDto)
                    .collect(Collectors.toList());
        }

        model.addAttribute("crewList", crewList);
        model.addAttribute("filtro", filtro == null ? new FiltroCrew() : filtro);
        return "crew";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") FiltroCrew filtro, Model model) {
        return doListar(model, filtro);
    }

    @GetMapping("/edit")
    public String doEditar(@RequestParam(value = "id", required = false) Integer id, Model model) {
        CrewDTO dto;

        if (id != null) {
            dto = crewRepository.findById(id)
                    .map(CrewMapper::toDto)
                    .orElse(new CrewDTO());
        } else {
            dto = new CrewDTO();
        }

        // Para gestión de películas
        List<MovieDTO> relatedMovies = new ArrayList<>();
        List<MovieDTO> unrelatedMovies = new ArrayList<>();

        if (id != null) {
            relatedMovies = movieRepository.findMoviesByCrewId(id).stream()
                    .map(MovieMapper::toDTO)
                    .collect(Collectors.toList());

            unrelatedMovies = movieRepository.findMoviesNotRelatedToCrew(id).stream()
                    .map(MovieMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            unrelatedMovies = movieRepository.findAll().stream()
                    .map(MovieMapper::toDTO)
                    .collect(Collectors.toList());
        }

        model.addAttribute("crewForm", dto);
        model.addAttribute("relatedMovies", relatedMovies);
        model.addAttribute("unrelatedMovies", unrelatedMovies);
        return "editCrew";
    }

    @PostMapping("/save")
    public String doGuardar(@ModelAttribute("crewForm") CrewDTO dto, Model model) {
        // Validación de nombre único
        Crew existing = crewRepository.findByNameIgnoreCase(dto.getName());
        if (existing != null && (dto.getId() == null || !existing.getId().equals(dto.getId()))) {
            model.addAttribute("error", "Ya existe un miembro del crew con ese nombre");

            // Recargar el formulario con los datos actuales
            model.addAttribute("crewForm", dto);

            // Cargar listas desde /edit
            return doEditar(dto.getId(), model);
        }

        Crew crew = CrewMapper.toEntity(dto, movieRepository);
        crewRepository.save(crew);
        return "redirect:/crew/";
    }


    @GetMapping("/removeMovie")
    public String doQuitarRelacion(@RequestParam("crewId") Integer crewId,
                                   @RequestParam("movieId") Integer movieId,
                                   Model model) {
        // Buscar el crew por id
        Crew crew = crewRepository.findById(crewId).orElse(null);
        if (crew == null) {
            model.addAttribute("error", "Miembro del crew no encontrado");
            return "redirect:/crew/edit?id=" + crewId;
        }

        // Quitar la película de la lista de películas asociadas al crew
        crew.getMovies().removeIf(movie -> movie.getId().equals(movieId));

        // Guardar los cambios
        crewRepository.save(crew);

        // Redirigir a la página de edición para que se refresque la vista
        return "redirect:/crew/edit?id=" + crewId;
    }
    @PostMapping("/associateMoviesToCrew")
    public String doAsociarPeliculas(@ModelAttribute("crewForm") CrewDTO dto) {
        Crew crew = crewRepository.findById(dto.getId()).orElse(null);
        if (crew == null) {
            return "redirect:/crew/";
        }

        // Añadir las películas seleccionadas al set actual de crew.movies
        if (dto.getSelectedMovieIds() != null) {
            List<Movie> moviesToAdd = movieRepository.findAllById(dto.getSelectedMovieIds());
            crew.getMovies().addAll(moviesToAdd);
        }

        crewRepository.save(crew);

        return "redirect:/crew/edit?id=" + crew.getId();
    }


    @PostMapping("/delete")
    public String doBorrar(@RequestParam("id") Integer id) {
        crewRepository.deleteById(id);
        return "redirect:/crew/";
    }


}