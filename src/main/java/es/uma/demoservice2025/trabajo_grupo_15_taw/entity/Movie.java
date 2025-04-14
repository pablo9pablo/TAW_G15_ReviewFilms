package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "movie", schema = "tmdb4")
public class Movie {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "budget", precision = 15, scale = 2)
    private BigDecimal budget;

    @Column(name = "homepage")
    private String homepage;

    @Column(name = "original_language", length = 50)
    private String originalLanguage;

    @Column(name = "original_title", nullable = false)
    private String originalTitle;

    @Lob
    @Column(name = "overview")
    private String overview;

    @Column(name = "popularity", precision = 5, scale = 2)
    private BigDecimal popularity;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "revenue", precision = 15, scale = 2)
    private BigDecimal revenue;

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "tagline")
    private String tagline;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "vote_average", precision = 3, scale = 1)
    private BigDecimal voteAverage;

    @Column(name = "vote_count")
    private Integer voteCount;

}