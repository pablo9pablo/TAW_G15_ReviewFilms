
package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
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

    @ManyToMany(mappedBy = "favorites")
    private Set<User> usersfavorites = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MovieCast> movieCasts = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<Crew> crews = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "MOVIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "GENRE_ID"))
    private Set<Genre> genres = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "movie_keyword",
            joinColumns = @JoinColumn(name = "MOVIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "KEYWORD_ID"))
    private Set<Keyword> keywords = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "movie_production_company",
            joinColumns = @JoinColumn(name = "MOVIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCTION_COMPANY_ID"))
    private Set<ProductionCompany> productionCompanies = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<User> usersseen = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "watchlist")
    private Set<User> users = new LinkedHashSet<>();

    public BigDecimal calcularMedia(){
        double avg = reviews.stream().mapToInt(Review::getRating).average().orElse(0);
        return BigDecimal.valueOf(avg).setScale(1, RoundingMode.HALF_UP);
    }
}