package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "reviewfilms", uniqueConstraints = {
        @UniqueConstraint(name = "EMAIL", columnNames = {"EMAIL"})
})
public class User {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordhash;

    @Column(name = "NAME", nullable = false)
    private String username;

}