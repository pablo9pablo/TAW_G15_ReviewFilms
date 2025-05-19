package es.uma.demoservice2025.trabajo_grupo_15_taw.mapper;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.SeenMovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Seen;
import org.springframework.stereotype.Component;

@Component
public class SeenMapper {

    public SeenMovieDTO toDTO(Seen seen) {
        SeenMovieDTO dto = new SeenMovieDTO();
        dto.setMovieId(seen.getMovie().getId());
        dto.setTitle(seen.getMovie().getOriginalTitle());
        dto.setImageUrl(seen.getMovie().getImageUrl());
        dto.setRuntime(seen.getMovie().getRuntime());
        dto.setVoteAverage(seen.getMovie().getVoteAverage());
        return dto;
    }
}
