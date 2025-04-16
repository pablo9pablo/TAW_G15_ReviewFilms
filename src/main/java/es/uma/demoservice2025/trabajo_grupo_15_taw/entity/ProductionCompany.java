package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "production_company", schema = "reviewfilms", uniqueConstraints = {
        @UniqueConstraint(name = "NAME", columnNames = {"NAME"})
})
public class ProductionCompany {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

}