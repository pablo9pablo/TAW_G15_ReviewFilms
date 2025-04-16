package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import lombok.Data;


@Data
@Entity
@Table(name = "movie_cast", indexes = {
        @Index(name = "ACTOR_ID", columnList = "ACTOR_ID")
})
public class MovieCastEntity {
    @EmbeddedId
    private MovieCastEntityId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private MovieEntity movie;

    @MapsId("actorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ACTOR_ID", nullable = false)
    private ActorEntity actor;

    @Column(name = "`CHARACTER`")
    private String character;

    @Column(name = "CREDIT_ID", nullable = false, length = 60)
    private String creditId;

    @Column(name = "CREDIT_ORDER")
    private Integer creditOrder;

}