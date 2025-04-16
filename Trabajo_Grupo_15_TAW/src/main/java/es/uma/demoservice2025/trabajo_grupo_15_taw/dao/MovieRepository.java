package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {

    @Query("select m from MovieEntity m where m.title like concat('%', :titulo, '%')")
    public List<MovieEntity> buscarPorTitulo(@Param("titulo") String titulo);
}
