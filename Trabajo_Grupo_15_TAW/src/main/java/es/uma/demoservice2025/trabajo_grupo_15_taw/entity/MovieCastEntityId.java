package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;
import java.util.Objects;
import lombok.Data;


@Data
@Embeddable
public class MovieCastEntityId implements java.io.Serializable {
    private static final long serialVersionUID = 2952035852259321374L;
    @Column(name = "MOVIE_ID", nullable = false)
    private Integer movieId;

    @Column(name = "ACTOR_ID", nullable = false)
    private Integer actorId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MovieCastEntityId entity = (MovieCastEntityId) o;
        return Objects.equals(this.actorId, entity.actorId) &&
                Objects.equals(this.movieId, entity.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, movieId);
    }

}