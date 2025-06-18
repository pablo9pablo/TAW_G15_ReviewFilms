package es.uma.demoservice2025.trabajo_grupo_15_taw.service;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.CrewRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.CrewDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Crew;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.CrewMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CrewService {

    private final CrewRepository crewRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public CrewService(CrewRepository crewRepository, MovieRepository movieRepository) {
        this.crewRepository = crewRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional(readOnly = true)
    public List<CrewDTO> findAll() {
        return crewRepository.findAll().stream()
                .map(CrewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CrewDTO> findByNameContaining(String name) {
        return crewRepository.findByNameContainingIgnoreCase(name).stream()
                .map(CrewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<CrewDTO> findById(Integer id) {
        return crewRepository.findById(id)
                .map(CrewMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<MovieDTO> findRelatedMovies(Integer crewId) {
        return movieRepository.findMoviesByCrewId(crewId).stream()
                .map(MovieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieDTO> findUnrelatedMovies(Integer crewId) {
        if (crewId == null) {
            return movieRepository.findAll().stream()
                    .map(MovieMapper::toDTO)
                    .collect(Collectors.toList());
        }
        return movieRepository.findMoviesNotRelatedToCrew(crewId).stream()
                .map(MovieMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean existsByNameIgnoreCase(String name, Integer excludeId) {
        Crew existing = crewRepository.findByNameIgnoreCase(name);
        return existing != null && (excludeId == null || !existing.getId().equals(excludeId));
    }

    public CrewDTO save(CrewDTO dto) {
        Crew crew = CrewMapper.toEntity(dto, movieRepository);
        Crew savedCrew = crewRepository.save(crew);
        return CrewMapper.toDto(savedCrew);
    }

    public boolean removeMovieFromCrew(Integer crewId, Integer movieId) {
        Optional<Crew> crewOpt = crewRepository.findById(crewId);
        if (crewOpt.isEmpty()) {
            return false;
        }

        Crew crew = crewOpt.get();
        boolean removed = crew.getMovies().removeIf(movie -> movie.getId().equals(movieId));

        if (removed) {
            crewRepository.save(crew);
        }

        return removed;
    }

    public boolean associateMoviesToCrew(Integer crewId, List<Integer> movieIds) {
        Optional<Crew> crewOpt = crewRepository.findById(crewId);
        if (crewOpt.isEmpty()) {
            return false;
        }

        Crew crew = crewOpt.get();
        if (movieIds != null && !movieIds.isEmpty()) {
            List<Movie> moviesToAdd = movieRepository.findAllById(movieIds);
            crew.getMovies().addAll(moviesToAdd);
            crewRepository.save(crew);
        }

        return true;
    }

    public boolean deleteById(Integer id) {
        if (crewRepository.existsById(id)) {
            crewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}