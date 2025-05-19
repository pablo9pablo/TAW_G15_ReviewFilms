package es.uma.demoservice2025.trabajo_grupo_15_taw.mapper;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.FavoriteMovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Favorite;
import org.springframework.stereotype.Component;

@Component
public class FavoriteMapper {

    public FavoriteMovieDTO toDTO(Favorite favorite) {
        if (favorite == null || favorite.getMovie() == null) {
            return null;
        }
        FavoriteMovieDTO dto = new FavoriteMovieDTO();
        dto.setMovieId(favorite.getMovie().getId());
        dto.setOriginalTitle(favorite.getMovie().getOriginalTitle());
        dto.setRuntime(favorite.getMovie().getRuntime());
        dto.setVoteAverage(favorite.getMovie().getVoteAverage());
        dto.setImageUrl(favorite.getMovie().getImageUrl());
        return dto;
    }
}
