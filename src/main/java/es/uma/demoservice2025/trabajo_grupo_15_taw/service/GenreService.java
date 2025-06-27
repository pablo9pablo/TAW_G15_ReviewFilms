package es.uma.demoservice2025.trabajo_grupo_15_taw.service;


// OUAIL BOUAZZA MANSOURI : 100%
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GenreService {

    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;
    private final MovieGenreRepository movieGenreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository,
                        MovieRepository movieRepository,
                        MovieGenreRepository movieGenreRepository) {
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
        this.movieGenreRepository = movieGenreRepository;
    }

    @Transactional(readOnly = true)
    public List<GenreDTO> findAll() {
        return genreRepository.findAll().stream()
                .map(GenreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GenreDTO> findByNameContaining(String name) {
        return genreRepository.findByNameContainingIgnoreCase(name).stream()
                .map(GenreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<GenreDTO> findById(Integer id) {
        return genreRepository.findById(id)
                .map(GenreMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<MovieDTO> findRelatedMovies(Integer genreId) {
        if (genreId == null) {
            return new ArrayList<>();
        }
        return movieGenreRepository.findByGenreId(genreId).stream()
                .map(MovieGenre::getMovie)
                .map(MovieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieDTO> findUnrelatedMovies(Integer genreId) {
        if (genreId == null) {
            return movieRepository.findAll().stream()
                    .map(MovieMapper::toDTO)
                    .collect(Collectors.toList());
        }

        List<MovieDTO> relatedMovies = findRelatedMovies(genreId);
        return movieRepository.findAll().stream()
                .filter(movie -> relatedMovies.stream()
                        .noneMatch(rm -> rm.getId().equals(movie.getId())))
                .map(MovieMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean existsByNameIgnoreCase(String name, Integer excludeId) {
        Genre existing = genreRepository.findByNameIgnoreCase(name);
        return existing != null && (excludeId == null || !existing.getId().equals(excludeId));
    }

    public GenreDTO save(GenreDTO dto) {
        Genre genre = GenreMapper.toEntity(dto);
        Genre savedGenre = genreRepository.save(genre);
        return GenreMapper.toDto(savedGenre);
    }

    public boolean deleteById(Integer id) {
        if (genreRepository.existsById(id)) {
            // Eliminar todas las relaciones MovieGenre primero
            List<MovieGenre> relaciones = movieGenreRepository.findByGenreId(id);
            movieGenreRepository.deleteAll(relaciones);

            // Luego eliminar el género
            genreRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean associateMoviesToGenre(Integer genreId, List<Integer> movieIds) {
        if (genreId == null || movieIds == null || movieIds.isEmpty()) {
            return false;
        }

        Optional<Genre> genreOpt = genreRepository.findById(genreId);
        if (genreOpt.isEmpty()) {
            return false;
        }

        Genre genre = genreOpt.get();
        for (Integer movieId : movieIds) {
            Optional<Movie> movieOpt = movieRepository.findById(movieId);
            if (movieOpt.isPresent()) {
                Movie movie = movieOpt.get();

                // Crear y guardar nueva relación
                MovieGenreId id = new MovieGenreId();
                id.setMovieId(movieId);
                id.setGenreId(genreId);

                MovieGenre relacion = new MovieGenre();
                relacion.setId(id);
                relacion.setMovie(movie);
                relacion.setGenre(genre);

                movieGenreRepository.save(relacion);
            }
        }

        return true;
    }

    public boolean removeMovieFromGenre(Integer genreId, Integer movieId) {
        if (genreId == null || movieId == null) {
            return false;
        }

        MovieGenreId id = new MovieGenreId();
        id.setMovieId(movieId);
        id.setGenreId(genreId);

        if (movieGenreRepository.existsById(id)) {
            movieGenreRepository.deleteById(id);
            return true;
        }

        return false;
    }
}