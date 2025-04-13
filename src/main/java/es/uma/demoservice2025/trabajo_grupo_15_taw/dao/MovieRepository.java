package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
