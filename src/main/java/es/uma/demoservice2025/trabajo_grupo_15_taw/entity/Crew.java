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
@Table(name = "crew", schema = "tmdb4")
public class Crew {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "name", nullable = false)
    private String name;

}