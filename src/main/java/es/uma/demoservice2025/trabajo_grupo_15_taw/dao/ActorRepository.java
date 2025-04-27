package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;


import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Actor;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
