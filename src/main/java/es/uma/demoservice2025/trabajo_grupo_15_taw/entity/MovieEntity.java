package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Data;


@Data
@Entity
@Table(name = "movie")
public class MovieEntity {
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
    @Column(name = "OVERVIEW")
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

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @ManyToMany
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "MOVIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "GENRE_ID"))
    private Set<GenreEntity> genres = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "movie_keyword",
            joinColumns = @JoinColumn(name = "MOVIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "KEYWORD_ID"))
    private Set<KeywordEntity> keywords = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "movie_production_company",
            joinColumns = @JoinColumn(name = "MOVIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCTION_COMPANY_ID"))
    private Set<ProductionCompanyEntity> productionCompanies = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<ReviewEntity> reviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<MovieCastEntity> movieCasts = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "favorites",
            joinColumns = @JoinColumn(name = "MOVIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private Set<User> favoriteUsers = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "watchlist",
            joinColumns = @JoinColumn(name = "MOVIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private Set<User> watchlistUsers = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "seen",
            joinColumns = @JoinColumn(name = "MOVIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private Set<User> seenUsers = new LinkedHashSet<>();
}
