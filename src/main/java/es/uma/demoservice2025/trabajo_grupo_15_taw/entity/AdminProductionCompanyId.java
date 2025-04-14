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
public class AdminProductionCompanyId implements java.io.Serializable {
    private static final long serialVersionUID = -7891543601517459892L;
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ISO_3166_1", nullable = false, length = 45)
    private String iso31661;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AdminProductionCompanyId entity = (AdminProductionCompanyId) o;
        return Objects.equals(this.iso31661, entity.iso31661) &&
                Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iso31661, id);
    }

}