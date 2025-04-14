package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin_production_companies", schema = "tmdb4")
public class AdminProductionCompany {
    @EmbeddedId
    private AdminProductionCompanyId id;

    @MapsId("iso31661")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ISO_3166_1", nullable = false)
    private AdminProductionCountry iso31661;

    @Column(name = "NAME")
    private String name;

}