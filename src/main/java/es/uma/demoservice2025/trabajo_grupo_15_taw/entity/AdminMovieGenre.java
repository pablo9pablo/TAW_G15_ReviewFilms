package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin_movie_genres", schema = "tmdb4")
public class AdminMovieGenre {
    @EmbeddedId
    private AdminMovieGenreId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private AdminMovie movie;

    @MapsId("genreId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "GENRE_ID", nullable = false)
    private AdminGenre genre;

}