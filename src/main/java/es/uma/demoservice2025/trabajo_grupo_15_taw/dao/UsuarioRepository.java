// PABLO MARTINEZ PALOP : 60%
// OUAIL BOUAZZA MANSOURI : 40%

package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    boolean existsByEmail(String email);

    // usamos fetch
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userRoles WHERE u.email = :email")
    User findByEmailFetchRoles(@Param("email") String email);

    @Query("SELECT u.email, COUNT(r) as reviewCount " +
            "FROM User u " +
            "LEFT JOIN u.reviews r " +
            "GROUP BY u.email " +
            "ORDER BY reviewCount DESC " +
            "LIMIT 3")
    List<Object[]> findTopUsersByReviews();

    @Query("SELECT u.email, COUNT(f) as favCount " +
            "FROM User u " +
            "LEFT JOIN u.favorites f " +
            "GROUP BY u.email " +
            "ORDER BY favCount DESC " +
            "LIMIT 3")
    List<Object[]> findTopUsersByFavorites();
}
