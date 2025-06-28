// OUAIL BOUAZZA MANSOURI: 50%
// MANUEL GALÁN ALFARO: 50%
package es.uma.demoservice2025.trabajo_grupo_15_taw.service;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;

import java.util.Optional;
import java.util.Set;

public class MovieDetailsResult {
    public final Movie movie;
    public final Optional<Integer> hasReview;
    public final Set<Movie> relatedMoviesGenre;
    public final Set<Movie> relatedMoviesProductionCompany;

    public MovieDetailsResult(Movie movie, Optional<Integer> hasReview,
                              Set<Movie> relatedMoviesGenre, Set<Movie> relatedMoviesProductionCompany) {
        this.movie = movie;
        this.hasReview = hasReview;
        this.relatedMoviesGenre = relatedMoviesGenre;
        this.relatedMoviesProductionCompany = relatedMoviesProductionCompany;
    }
}