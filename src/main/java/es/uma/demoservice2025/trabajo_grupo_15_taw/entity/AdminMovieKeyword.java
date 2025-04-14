package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin_movie_keywords", schema = "tmdb4")
public class AdminMovieKeyword {
    @EmbeddedId
    private AdminMovieKeywordId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private AdminMovie movie;

    @MapsId("keywordId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "KEYWORD_ID", nullable = false)
    private AdminKeyword keyword;

}