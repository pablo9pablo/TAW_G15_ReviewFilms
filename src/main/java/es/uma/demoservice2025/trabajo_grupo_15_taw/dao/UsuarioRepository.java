package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
