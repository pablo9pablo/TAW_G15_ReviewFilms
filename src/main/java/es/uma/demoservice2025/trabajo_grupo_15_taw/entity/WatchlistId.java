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
public class WatchlistId implements java.io.Serializable {
    private static final long serialVersionUID = 180632452159392700L;
    @Column(name = "MOVIE_ID", nullable = false)
    private Integer movieId;

    @Column(name = "USER_ID", nullable = false)
    private Integer userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WatchlistId entity = (WatchlistId) o;
        return Objects.equals(this.movieId, entity.movieId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, userId);
    }

}