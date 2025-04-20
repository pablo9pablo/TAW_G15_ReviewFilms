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
public class MovieKeywordId implements java.io.Serializable {
    private static final long serialVersionUID = 3818137447728785600L;
    @Column(name = "MOVIE_ID", nullable = false)
    private Integer movieId;

    @Column(name = "KEYWORD_ID", nullable = false)
    private Integer keywordId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MovieKeywordId entity = (MovieKeywordId) o;
        return Objects.equals(this.keywordId, entity.keywordId) &&
                Objects.equals(this.movieId, entity.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keywordId, movieId);
    }

}