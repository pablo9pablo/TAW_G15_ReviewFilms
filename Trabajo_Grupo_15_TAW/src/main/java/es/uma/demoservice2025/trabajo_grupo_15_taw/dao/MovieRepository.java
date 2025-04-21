package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.ProductionCompany;
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



    // Consultas para la recomendación de películas
    @Query("SELECT g FROM Genre g JOIN MovieGenre mg ON g.id = mg.genre.id WHERE mg.movie.id = :movieId")
    List<Genre> findGenresByMovieId(@Param("movieId") Integer movieId);

    @Query("SELECT m FROM Movie m JOIN MovieGenre mg ON m.id = mg.movie.id WHERE mg.genre.id = :genreId AND mg.movie.id <> :movieId")
    List<Movie> findMoviesByGenre(@Param("genreId") Integer genreId, @Param("movieId") Integer movieId);


    @Query("SELECT pc FROM ProductionCompany pc JOIN MovieProductionCompany mpc ON pc.id = mpc.productionCompany.id WHERE mpc.movie.id = :movieId")
    List<ProductionCompany> findProductionCompanyByMovieId(@Param("movieId") Integer movieId);

    @Query("SELECT m FROM Movie m JOIN MovieProductionCompany mpc ON m.id = mpc.movie.id WHERE mpc.productionCompany.id = :productionCompanyId AND mpc.movie.id <> :movieId")
    List<Movie> findMoviesByProductionCompany(@Param("productionCompanyId") Integer productionCompanyId, @Param("movieId") Integer movieId);
}
