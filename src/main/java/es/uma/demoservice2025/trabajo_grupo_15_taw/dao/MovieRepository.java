package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {
}
