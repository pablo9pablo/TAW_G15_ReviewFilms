package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "favorites", schema = "reviewfilms", indexes = {
        @Index(name = "USER_ID", columnList = "USER_ID")
})
public class Favorite {
    @EmbeddedId
    private FavoriteId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie movie;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "USER_ID", nullable = false)
    private es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User user;

}