package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    //Busqueda
    @Query("select m from Movie m where m.title like concat('%', :titulo, '%')")
    public List<Movie> buscarPorTitulo(@Param("titulo") String titulo);

    //Filtrado
    @Query("SELECT m FROM Movie m " +
            "JOIN m.reviews r " +
            "JOIN MovieGenre mg ON mg.movie = m " +
            "JOIN mg.genre g " +
            "WHERE (:anyo IS NULL OR YEAR(m.releaseDate) = :anyo) " +
            "AND (:pop IS NULL OR m.popularity = :pop) " +
            "AND (:genre IS NULL OR g.name = :genre)")
    public List<Movie> buscarPorFiltros(@Param("anyo") Integer anyo, @Param("pop") Double pop, @Param("genre")  String genre);
}
