package es.uma.demoservice2025.trabajo_grupo_15_taw.service;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import lombok.Data;

import java.util.List;

@Data
public class Recomendaciones {
    public final List<Movie> movieList;
    public final List<Movie> bestRatingMovieList;
    public final List<Movie> moreCommentedMovieList;
    public final List<Movie> blockbusters;
    public final List<Movie> releasesTenYearsMovieList;
    public final List<Movie> superheroeMovieList;
    public final List<Movie> dramaMovieList;
    public final List<Movie> comedyMovieList;
    public final List<Movie> warMovieList;
    public final List<Movie> basedTrueStoryMovieList;
    public final List<Genre> genreList;

    public Recomendaciones(List<Movie> movieList, List<Movie> bestRatingMovieList,
                           List<Movie> moreCommentedMovieList, List<Movie> blockbusters,
                           List<Movie> releasesTenYearsMovieList, List<Movie> superheroeMovieList,
                           List<Movie> dramaMovieList, List<Movie> comedyMovieList,
                           List<Movie> warMovieList, List<Movie> basedTrueStoryMovieList,
                           List<Genre> genreList) {
        this.movieList = movieList;
        this.bestRatingMovieList = bestRatingMovieList;
        this.moreCommentedMovieList = moreCommentedMovieList;
        this.blockbusters = blockbusters;
        this.releasesTenYearsMovieList = releasesTenYearsMovieList;
        this.superheroeMovieList = superheroeMovieList;
        this.dramaMovieList = dramaMovieList;
        this.comedyMovieList = comedyMovieList;
        this.warMovieList = warMovieList;
        this.basedTrueStoryMovieList = basedTrueStoryMovieList;
        this.genreList = genreList;
    }
}