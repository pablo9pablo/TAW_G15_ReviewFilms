package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "actor", schema = "reviewfilms")
public class Actor {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "GENDER")
    private String gender;

    @OneToMany(mappedBy = "actor")
    private Set<MovieCast> movieCasts = new LinkedHashSet<>();

}