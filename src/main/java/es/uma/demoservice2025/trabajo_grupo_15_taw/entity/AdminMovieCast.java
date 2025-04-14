package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin_movie_cast", schema = "tmdb4")
public class AdminMovieCast {
    @EmbeddedId
    private AdminMovieCastId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private AdminMovie movie;

    @MapsId("actorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ACTOR_ID", nullable = false)
    private AdminActor actor;

    @Column(name = "CAST_CREDIT_ID")
    private Integer castCreditId;

    @Column(name = "`CHARACTER`")
    private String character;

    @Column(name = "CREDIT_ORDER")
    private Integer creditOrder;

}