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
public class MovieProductionCompanyId implements java.io.Serializable {
    private static final long serialVersionUID = -7757644189899279352L;
    @Column(name = "MOVIE_ID", nullable = false)
    private Integer movieId;

    @Column(name = "PRODUCTION_COMPANY_ID", nullable = false)
    private Integer productionCompanyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MovieProductionCompanyId entity = (MovieProductionCompanyId) o;
        return Objects.equals(this.movieId, entity.movieId) &&
                Objects.equals(this.productionCompanyId, entity.productionCompanyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, productionCompanyId);
    }

}