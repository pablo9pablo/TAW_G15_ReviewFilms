package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.ProductionCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    //Busqueda
    @Query("select m from Movie m where m.title like concat('%', :texto, '%')")
    public List<Movie> buscarPorTitulo(@Param("texto") String texto);

    //Filtrado Sin genero
    @Query("SELECT m FROM Movie m " +
            "WHERE (:anyo IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) ")
    public List<Movie> buscarPorFiltrosSinGenero(@Param("anyo") Integer anyo,
                                                 @Param("vote") Double vote,
                                                 @Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);

    //Filtrado con genero
    @Query("SELECT m FROM Movie m " +
            "JOIN MovieGenre mg ON mg.movie = m " +
            "JOIN mg.genre g " +
            "WHERE (:anyo IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (:generoIds IS NULL OR g.id in :generoIds)")
    public List<Movie> buscarPorFiltrosConGenero(@Param("anyo") Integer anyo,
                                                @Param("vote") Double vote,
                                                @Param("generoIds") List<Integer>genreList,
                                                @Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);
    

    // Consultas para la recomendación de películas
    @Query("SELECT g FROM Genre g JOIN MovieGenre mg ON g.id = mg.genre.id WHERE mg.movie.id = :movieId")
    List<Genre> findGenresByMovieId(@Param("movieId") Integer movieId);

    @Query("SELECT m FROM Movie m JOIN MovieGenre mg ON m.id = mg.movie.id WHERE mg.genre.id = :genreId AND mg.movie.id <> :movieId")
    List<Movie> findMoviesByGenre(@Param("genreId") Integer genreId, @Param("movieId") Integer movieId);


    @Query("SELECT pc FROM ProductionCompany pc JOIN MovieProductionCompany mpc ON pc.id = mpc.productionCompany.id WHERE mpc.movie.id = :movieId")
    List<ProductionCompany> findProductionCompanyByMovieId(@Param("movieId") Integer movieId);

    @Query("SELECT m FROM Movie m JOIN MovieProductionCompany mpc ON m.id = mpc.movie.id WHERE mpc.productionCompany.id = :productionCompanyId AND mpc.movie.id <> :movieId")
    List<Movie> findMoviesByProductionCompany(@Param("productionCompanyId") Integer productionCompanyId, @Param("movieId") Integer movieId);

    @Query("SELECT DISTINCT m FROM Movie m JOIN MovieGenre mg ON m.id = mg.movie.id JOIN Genre g ON mg.genre.id = g.id WHERE g.id = 21 ORDER BY m.releaseDate ASC")
    List<Movie> findAllSuperheroMovies();


}
