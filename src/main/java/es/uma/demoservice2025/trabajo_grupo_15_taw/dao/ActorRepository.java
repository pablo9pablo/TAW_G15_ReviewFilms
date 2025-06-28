// PABLO MARTINEZ PALOP : 60%
// OUAIL BOUAZZA MANSOURI : 40%

package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;


import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
    List<Actor> findByNameContainingIgnoreCase(String name);


    @Query("SELECT a.name, COUNT(mc) as movieCount " +
                "FROM Actor a " +
                "JOIN a.movieCasts mc " +
                "GROUP BY a.name " +
                "ORDER BY movieCount DESC " +
                "LIMIT 5")
    List<Object[]> findTopActors();
}
