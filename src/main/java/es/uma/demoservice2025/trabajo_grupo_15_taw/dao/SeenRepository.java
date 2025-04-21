package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Review;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Seen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeenRepository extends JpaRepository<Seen,Integer> {


}
