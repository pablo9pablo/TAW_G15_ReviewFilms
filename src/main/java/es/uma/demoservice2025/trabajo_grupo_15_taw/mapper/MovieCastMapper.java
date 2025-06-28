package es.uma.demoservice2025.trabajo_grupo_15_taw.mapper;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieCastDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieCast;

// OUAIL BOUAZZA MANSOURI : 100%
public class MovieCastMapper {

    public MovieCastDTO toDto(MovieCast movieCast) {
        MovieCastDTO dto = new MovieCastDTO();
        dto.setMovieId(movieCast.getMovie().getId());
        dto.setActorId(movieCast.getActor().getId());
        dto.setActorName(movieCast.getActor().getName());
        dto.setCharacter(movieCast.getCharacter());
        dto.setCreditOrder(movieCast.getCreditOrder());
        dto.setCreditId(movieCast.getCreditId());
        return dto;
    }
}
