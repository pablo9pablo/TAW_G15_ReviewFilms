package es.uma.demoservice2025.trabajo_grupo_15_taw.service;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.ActorRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ActorDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Actor;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.ActorMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ActorService {

    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository, MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional(readOnly = true)
    public List<ActorDTO> findAllActors() {
        return actorRepository.findAll().stream()
                .map(ActorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ActorDTO> findActorsByName(String name) {
        return actorRepository.findByNameContainingIgnoreCase(name).stream()
                .map(ActorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ActorDTO> findActorById(Integer id) {
        return actorRepository.findById(id)
                .map(ActorMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<MovieDTO> findRelatedMovies(Integer actorId) {
        return movieRepository.findMoviesByActorId(actorId).stream()
                .map(MovieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieDTO> findUnrelatedMovies(Integer actorId) {
        return movieRepository.findMoviesNotRelatedToActor(actorId).stream()
                .map(MovieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieDTO> findAllMovies() {
        return movieRepository.findAll().stream()
                .map(MovieMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean isNameAlreadyTaken(String name, Integer actorId) {
        Actor existing = actorRepository.findByNameIgnoreCase(name);
        return existing != null && (actorId == null || !existing.getId().equals(actorId));
    }

    public ActorDTO saveActor(ActorDTO dto) {
        Actor actor = ActorMapper.toEntity(dto);
        Actor savedActor = actorRepository.save(actor);
        return ActorMapper.toDto(savedActor);
    }

    public void deleteActor(Integer id) {
        actorRepository.deleteById(id);
    }

    public void associateMoviesToActor(Integer actorId, List<Integer> movieIds) {
        if (movieIds != null) {
            for (Integer movieId : movieIds) {
                movieRepository.associateActorToMovie(actorId, movieId);
            }
        }
    }

    public void removeMovieFromActor(Integer actorId, Integer movieId) {
        movieRepository.removeActorFromMovie(actorId, movieId);
    }
}