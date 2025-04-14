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
public class MovieGenreId implements java.io.Serializable {
    private static final long serialVersionUID = 784340238457437389L;
    @Column(name = "GENRE_ID", nullable = false)
    private Integer genreId;

    @Column(name = "MOVIE_ID", nullable = false)
    private Integer movieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MovieGenreId entity = (MovieGenreId) o;
        return Objects.equals(this.genreId, entity.genreId) &&
                Objects.equals(this.movieId, entity.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, movieId);
    }

}