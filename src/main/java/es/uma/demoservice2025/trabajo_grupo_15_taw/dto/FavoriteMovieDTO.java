// MIGUEL LABELLA RAMÍREZ: 100%
package es.uma.demoservice2025.trabajo_grupo_15_taw.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FavoriteMovieDTO {

    private Integer movieId;
    private String originalTitle;
    private Integer runtime;
    private BigDecimal voteAverage;
    private String imageUrl;

}
