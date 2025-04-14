package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "movie_cast", schema = "tmdb4", indexes = {
        @Index(name = "ACTOR_ID", columnList = "ACTOR_ID")
})
public class MovieCast {
    @EmbeddedId
    private MovieCastId id;

    @MapsId("actorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ACTOR_ID", nullable = false)
    private Actor actor;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private Movie movie;

    @Column(name = "`character`")
    private String character;

    @Column(name = "credit_id", nullable = false, length = 60)
    private String creditId;

    @Column(name = "credit_order")
    private Integer creditOrder;

}