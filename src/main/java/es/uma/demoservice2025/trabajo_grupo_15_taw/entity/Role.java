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
@Table(name = "role", schema = "reviewfilms", uniqueConstraints = {
        @UniqueConstraint(name = "NAME", columnNames = {"NAME"})
})
public class Role {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private Set<User> users = new LinkedHashSet<>();

}