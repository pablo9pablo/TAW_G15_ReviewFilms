package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "production_company", schema = "tmdb4", uniqueConstraints = {
        @UniqueConstraint(name = "NAME", columnNames = {"name"})
})
public class ProductionCompany {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

}