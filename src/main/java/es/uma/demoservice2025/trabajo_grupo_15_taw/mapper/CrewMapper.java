package es.uma.demoservice2025.trabajo_grupo_15_taw.mapper;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.CrewDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Crew;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CrewMapper {

    public static CrewDTO toDto(Crew entity) {
        if (entity == null) return null;
        CrewDTO dto = new CrewDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setGender(entity.getGender());

        if (entity.getMovies() != null) {
            dto.setSelectedMovieIds(
                    entity.getMovies().stream()
                            .map(Movie::getId)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public static Crew toEntity(CrewDTO dto, MovieRepository movieRepo) {
        if (dto == null) return null;
        Crew entity = new Crew();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setGender(dto.getGender());

        if (dto.getSelectedMovieIds() != null) {
            Set<Movie> selectedMovies = dto.getSelectedMovieIds().stream()
                    .map(movieRepo::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            entity.setMovies(selectedMovies);
        }

        return entity;
    }
}
