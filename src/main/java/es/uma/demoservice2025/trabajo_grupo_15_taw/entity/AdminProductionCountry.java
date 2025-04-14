package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin_production_countries", schema = "tmdb4")
public class AdminProductionCountry {
    @Id
    @Column(name = "ISO_3166_1", nullable = false, length = 45)
    private String iso31661;

    @Column(name = "NAME")
    private String name;

}