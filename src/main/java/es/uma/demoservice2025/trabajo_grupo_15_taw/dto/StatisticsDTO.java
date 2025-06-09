//PABLO MARTINEZ PALOP : 10%
package es.uma.demoservice2025.trabajo_grupo_15_taw.dto;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import lombok.Data;

import java.util.List;

@Data
public class StatisticsDTO {
    private List<Object[]> topRatedMovies;
    private List<Object[]> worstRatedMovies;
    private List<Object[]> mostReviewedMovies;
    private List<Movie> highestGrossingMovies;
    private List<Object[]> moviesByGenre;
    private long totalMovies;
    private List<Object[]> topProductionCompanies;
    private List<Object[]> topReviewers;
    private List<Object[]> topFavorites;
    private List<Object[]> topActors;
}
