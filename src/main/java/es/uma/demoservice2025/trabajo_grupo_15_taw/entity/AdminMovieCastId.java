package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class AdminMovieCastId implements java.io.Serializable {
    private static final long serialVersionUID = -1147442279933777093L;
    @Column(name = "MOVIE_ID", nullable = false)
    private Integer movieId;

    @Column(name = "ACTOR_ID", nullable = false)
    private Integer actorId;

    @Column(name = "CREDIT_ID", nullable = false, length = 60)
    private String creditId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AdminMovieCastId entity = (AdminMovieCastId) o;
        return Objects.equals(this.creditId, entity.creditId) &&
                Objects.equals(this.actorId, entity.actorId) &&
                Objects.equals(this.movieId, entity.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditId, actorId, movieId);
    }

}