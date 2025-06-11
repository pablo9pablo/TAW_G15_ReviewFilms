// MIGUEL LABELLA RAMÍREZ: 100%

package es.uma.demoservice2025.trabajo_grupo_15_taw.mapper;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.WatchlistDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Watchlist;
import org.springframework.stereotype.Component;

@Component
public class WatchlistMapper {

    public WatchlistDTO toDTO(Watchlist watchlist) {
        WatchlistDTO dto = new WatchlistDTO();
        dto.setMovieId(watchlist.getMovie().getId());
        dto.setOriginalTitle(watchlist.getMovie().getOriginalTitle());
        dto.setImageUrl(watchlist.getMovie().getImageUrl());
        dto.setRuntime(watchlist.getMovie().getRuntime());
        dto.setVoteAverage(watchlist.getMovie().getVoteAverage());
        return dto;
    }
}
