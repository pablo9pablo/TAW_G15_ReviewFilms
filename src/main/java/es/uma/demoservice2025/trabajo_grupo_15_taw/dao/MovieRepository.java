// PABLO MARTINEZ PALOP : 28%
// LUCIA ROSALES SANTIAGO: 5%
// MANUEL GALÁN ALFARO: 35%


package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.ProductionCompany;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    // busqueda
    @Query("""
    SELECT DISTINCT m FROM Movie m
    LEFT JOIN MovieGenre mg ON mg.movie = m
    LEFT JOIN mg.genre g
    WHERE (:texto IS NULL OR m.title LIKE CONCAT('%', :texto, '%') OR m.originalTitle LIKE CONCAT('%', :texto, '%'))
    AND (:anyo IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate)
    AND (:vote IS NULL OR m.voteAverage >= :vote)
    AND (:generoIds IS NULL OR g.id IN :generoIds)
""")
    List<Movie> buscarPeliculasConFiltros(
            @Param("texto") String texto,
            @Param("anyo") Integer anyo,
            @Param("vote") Double vote,
            @Param("generoIds") List<Integer> generoIds,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    // Consultas para la recomendación de películas
    @Query("SELECT g FROM Genre g JOIN MovieGenre mg ON g.id = mg.genre.id WHERE mg.movie.id = :movieId")
    List<Genre> findGenresByMovieId(@Param("movieId") Integer movieId);

    @Query("SELECT m FROM Movie m JOIN MovieGenre mg ON m.id = mg.movie.id WHERE mg.genre.id = :genreId AND mg.movie.id <> :movieId")
    List<Movie> findMoviesByGenre(@Param("genreId") Integer genreId, @Param("movieId") Integer movieId);

    @Query("SELECT pc FROM ProductionCompany pc JOIN MovieProductionCompany mpc ON pc.id = mpc.productionCompany.id WHERE mpc.movie.id = :movieId")
    List<ProductionCompany> findProductionCompanyByMovieId(@Param("movieId") Integer movieId);

    @Query("SELECT m FROM Movie m JOIN MovieProductionCompany mpc ON m.id = mpc.movie.id WHERE mpc.productionCompany.id = :productionCompanyId AND mpc.movie.id <> :movieId")
    List<Movie> findMoviesByProductionCompany(@Param("productionCompanyId") Integer productionCompanyId, @Param("movieId") Integer movieId);

    @Query("SELECT m " +
            "FROM Movie m " +
            "JOIN m.productionCompanies pc " +
            "WHERE pc.id = :productionCompanyId")
    List<Movie> findMoviesByProductionCompany(@Param("productionCompanyId") Integer productionCompanyId);

    @Query("SELECT m " +
            "FROM Movie m " +
            "WHERE :productionCompanyId " +
            "NOT IN (SELECT pc.id FROM m.productionCompanies pc)")
    List<Movie> findMoviesNotRelatedToProductionCompany(@Param("productionCompanyId") Integer productionCompanyId);

    @Query("SELECT DISTINCT m FROM Movie m JOIN MovieGenre mg ON m.id = mg.movie.id JOIN Genre g ON mg.genre.id = g.id WHERE g.id = :genreId ORDER BY m.releaseDate ASC")
    List<Movie> findAllMoviesByGenre(@Param("genreId") Integer genreId);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.keywords k WHERE k.id IN :keywordIds")
    List<Movie> findMoviesByKeywordIds(@Param("keywordIds") List<Integer> keywordIds);

    @Query("SELECT DISTINCT m FROM Movie m WHERE m.voteAverage >= :minRating ORDER BY m.voteAverage DESC")
    List<Movie> findTopMovies(@Param("minRating") Double minRating);

    @Query("SELECT DISTINCT m FROM Movie m WHERE m.voteCount >= :minNumReviews ORDER BY m.voteCount DESC")
    List<Movie> findMostReviewsMovies(@Param("minNumReviews") Integer minNumReviews);

    @Query("SELECT DISTINCT m FROM Movie m WHERE m.revenue >= :minRevenue ORDER BY m.revenue DESC")
    List<Movie> findBlockbusters(@Param("minRevenue") Double minRevenue);

    @Query("SELECT DISTINCT m FROM Movie m WHERE m.releaseDate >= :minDate ORDER BY m.releaseDate DESC")
    List<Movie> findTenYearsReleases(@Param("minDate") LocalDate minDate);


    @Query("SELECT m, AVG(r.rating) as avgRating, COUNT(r) as reviewCount " +
            "FROM Movie m " +
            "JOIN m.reviews r " +
            "GROUP BY m " +
            "HAVING COUNT(r) >= 1 " +
            "ORDER BY avgRating DESC")
    List<Object[]> findTopRatedMovies();

    @Query("SELECT m, AVG(r.rating) as avgRating, COUNT(r) as reviewCount " +
            "FROM Movie m " +
            "JOIN m.reviews r " +
            "GROUP BY m " +
            "HAVING COUNT(r) >= 1 " +
            "ORDER BY avgRating ASC")
    List<Object[]> findWorstRatedMovies();

    @Query("SELECT m, COUNT(r) as reviewCount " +
            "FROM Movie m " +
            "JOIN m.reviews r " +
            "GROUP BY m " +
            "ORDER BY reviewCount DESC")
    List<Object[]> findMoviesWithMostReviews();

    @Query("SELECT m " +
            "FROM Movie m " +
            "WHERE m.revenue > 0 " +
            "ORDER BY m.revenue DESC")
    List<Movie> findHighestGrossingMovies();

    @Query("SELECT g.name, COUNT(m) as movieCount " +
            "FROM Movie m " +
            "JOIN m.genres g " +
            "GROUP BY g.name " +
            "ORDER BY movieCount DESC " +
            "LIMIT 5")
    List<Object[]> findMovieCountByGenre();

    @Query("SELECT pc.name, COUNT(m) as movieCount " +
            "FROM Movie m " +
            "JOIN m.productionCompanies pc " +
            "GROUP BY pc.name " +
            "ORDER BY movieCount DESC " +
            "LIMIT 5")
    List<Object[]> findTopProductionCompanies();

    /// actor
    @Query("SELECT m FROM Movie m JOIN m.movieCasts mc WHERE mc.actor.id = :actorId")
    List<Movie> findMoviesByActorId(Integer actorId);

    @Query("SELECT m FROM Movie m WHERE m NOT IN " +
            "(SELECT m2 FROM Movie m2 JOIN m2.movieCasts mc WHERE mc.actor.id = :actorId)")
    List<Movie> findMoviesNotRelatedToActor(Integer actorId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO movie_cast (actor_id, movie_id) VALUES (:actorId, :movieId)", nativeQuery = true)
    void associateActorToMovie(Integer actorId, Integer movieId);

    @Modifying
    @Transactional
    @Query("DELETE FROM MovieCast mc WHERE mc.actor.id = :actorId AND mc.movie.id = :movieId")
    void removeActorFromMovie(Integer actorId, Integer movieId);

    /// crew
    @Query("SELECT m FROM Movie m JOIN m.crews c WHERE c.id = :crewId")
    List<Movie> findMoviesByCrewId(Integer crewId);

    @Query("SELECT m FROM Movie m WHERE m NOT IN " +
            "(SELECT m2 FROM Movie m2 JOIN m2.crews c WHERE c.id = :crewId)")
    List<Movie> findMoviesNotRelatedToCrew(Integer crewId);


}
