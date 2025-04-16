package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "movie_cast", schema = "reviewfilms", indexes = {
        @Index(name = "ACTOR_ID", columnList = "ACTOR_ID")
})
public class MovieCast {
    @EmbeddedId
    private MovieCastId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private Movie movie;

    @MapsId("actorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ACTOR_ID", nullable = false)
    private Actor actor;

    @Column(name = "`CHARACTER`")
    private String character;

    @Column(name = "CREDIT_ID", nullable = false, length = 60)
    private String creditId;

    @Column(name = "CREDIT_ORDER")
    private Integer creditOrder;

}