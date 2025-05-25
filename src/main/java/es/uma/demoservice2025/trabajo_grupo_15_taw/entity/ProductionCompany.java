package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "production_company", schema = "reviewfilms", uniqueConstraints = {
        @UniqueConstraint(name = "NAME", columnNames = {"NAME"})
})
public class ProductionCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "movie_production_company",
            joinColumns = @JoinColumn(name = "PRODUCTION_COMPANY_ID"),
            inverseJoinColumns = @JoinColumn(name = "MOVIE_ID"))
    private Set<Movie> movies = new LinkedHashSet<>();

}