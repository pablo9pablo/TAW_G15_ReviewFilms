package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "movie", schema = "reviewfilms")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "BUDGET", precision = 15, scale = 2)
    private BigDecimal budget;

    @Column(name = "HOMEPAGE")
    private String homepage;

    @Column(name = "ORIGINAL_LANGUAGE", length = 50)
    private String originalLanguage;

    @Column(name = "ORIGINAL_TITLE", nullable = false)
    private String originalTitle;

    @Lob
    @Column(name = "overview")
    private String overview;

    @Column(name = "POPULARITY", precision = 5, scale = 2)
    private BigDecimal popularity;

    @Column(name = "RELEASE_DATE")
    private LocalDate releaseDate;

    @Column(name = "REVENUE", precision = 15, scale = 2)
    private BigDecimal revenue;

    @Column(name = "RUNTIME")
    private Integer runtime;

    @Column(name = "STATUS", length = 50)
    private String status;

    @Column(name = "TAGLINE")
    private String tagline;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "VOTE_AVERAGE", precision = 3, scale = 1)
    private BigDecimal voteAverage;

    @Column(name = "VOTE_COUNT")
    private Integer voteCount;

    @Column(name = "IMAGE_URL", length = 500, nullable = true)
    private String imageUrl;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews;
}