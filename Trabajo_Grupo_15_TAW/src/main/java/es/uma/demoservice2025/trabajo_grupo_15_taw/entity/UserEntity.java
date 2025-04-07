package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Data;


@Data
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(name = "EMAIL", columnNames = {"EMAIL"})
})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "user")
    private Set<ReviewEntity> reviews = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "seen",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MOVIE_ID"))
    private Set<MovieEntity> moviesSeen = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "watchlist",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MOVIE_ID"))
    private Set<MovieEntity> moviesWatchList = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "favorites",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MOVIE_ID"))
    private Set<MovieEntity> favoriteMovies = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<RoleEntity> roles = new LinkedHashSet<>();

}