package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Data;


@Data
@Entity
@Table(name = "role", uniqueConstraints = {
        @UniqueConstraint(name = "NAME", columnNames = {"NAME"})
})
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private Set<User> users = new LinkedHashSet<>();

}