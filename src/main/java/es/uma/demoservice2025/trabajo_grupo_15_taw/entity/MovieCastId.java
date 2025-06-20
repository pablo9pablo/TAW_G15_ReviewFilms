package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class MovieCastId implements Serializable {

    private static final long serialVersionUID = 3138839905339632066L;

    @Column(name = "MOVIE_ID", nullable = false)
    private Integer movieId;

    @Column(name = "ACTOR_ID", nullable = false)
    private Integer actorId;

    public MovieCastId() {
    }

    public MovieCastId(Integer movieId, Integer actorId) {
        this.movieId = movieId;
        this.actorId = actorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MovieCastId that = (MovieCastId) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(actorId, that.actorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, actorId);
    }
}
