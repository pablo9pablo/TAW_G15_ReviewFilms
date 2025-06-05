package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Seen;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.SeenId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SeenRepository extends JpaRepository<Seen, SeenId> {

    List<Seen> findByUserId(Integer userId);

    // Filtrado con género
    @Query("SELECT s FROM Seen s " +
            "JOIN s.movie m " +
            "JOIN MovieGenre mg ON mg.movie = m " +
            "JOIN mg.genre g " +
            "WHERE s.user.id = :userId " +
            "AND (:anyo IS NULL OR YEAR(m.releaseDate) = :anyo) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (:generoIds IS NULL OR g.id IN :generoIds)")
    List<Seen> buscarPorFiltrosConGenero(@Param("userId") Integer userId,
                                         @Param("anyo") Integer anyo,
                                         @Param("vote") Double vote,
                                         @Param("generoIds") List<Integer> generoIds,
                                         @Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);

    // Filtrado sin género
    @Query("SELECT s FROM Seen s " +
            "JOIN s.movie m " +
            "WHERE s.user.id = :userId " +
            "AND (:anyo IS NULL OR YEAR(m.releaseDate) = :anyo) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote)")
    List<Seen> buscarPorFiltrosSinGenero(@Param("userId") Integer userId,
                                         @Param("anyo") Integer anyo,
                                         @Param("vote") Double vote,
                                         @Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);

    // Filtrado con género y orden ascendente
    @Query("SELECT s FROM Seen s " +
            "JOIN s.movie m " +
            "JOIN MovieGenre mg ON mg.movie = m " +
            "JOIN mg.genre g " +
            "WHERE s.user.id = :userId " +
            "AND (:anyo IS NULL OR YEAR(m.releaseDate) = :anyo) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (:generoIds IS NULL OR g.id IN :generoIds) " +
            "ORDER BY m.title ASC")
    List<Seen> buscarPorFiltrosConGeneroOrdenAsc(@Param("userId") Integer userId,
                                                 @Param("anyo") Integer anyo,
                                                 @Param("vote") Double vote,
                                                 @Param("generoIds") List<Integer> generoIds,
                                                 @Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);

    // Filtrado con género y orden descendente
    @Query("SELECT s FROM Seen s " +
            "JOIN s.movie m " +
            "JOIN MovieGenre mg ON mg.movie = m " +
            "JOIN mg.genre g " +
            "WHERE s.user.id = :userId " +
            "AND (:anyo IS NULL OR YEAR(m.releaseDate) = :anyo) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (:generoIds IS NULL OR g.id IN :generoIds) " +
            "ORDER BY m.title DESC")
    List<Seen> buscarPorFiltrosConGeneroOrdenDesc(@Param("userId") Integer userId,
                                                  @Param("anyo") Integer anyo,
                                                  @Param("vote") Double vote,
                                                  @Param("generoIds") List<Integer> generoIds,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);

    // Filtrado sin género y orden ascendente
    @Query("SELECT s FROM Seen s " +
            "JOIN s.movie m " +
            "WHERE s.user.id = :userId " +
            "AND (:anyo IS NULL OR YEAR(m.releaseDate) = :anyo) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "ORDER BY m.title ASC")
    List<Seen> buscarPorFiltrosSinGeneroOrdenAsc(@Param("userId") Integer userId,
                                                 @Param("anyo") Integer anyo,
                                                 @Param("vote") Double vote,
                                                 @Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);

    // Filtrado sin género y orden descendente
    @Query("SELECT s FROM Seen s " +
            "JOIN s.movie m " +
            "WHERE s.user.id = :userId " +
            "AND (:anyo IS NULL OR YEAR(m.releaseDate) = :anyo) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "ORDER BY m.title DESC")
    List<Seen> buscarPorFiltrosSinGeneroOrdenDesc(@Param("userId") Integer userId,
                                                  @Param("anyo") Integer anyo,
                                                  @Param("vote") Double vote,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);

    Seen findByUserIdAndMovieId(Integer userId, Integer movieId);

    @Query("SELECT COUNT(s) FROM Seen s WHERE s.user.id = :userId")
    long contarPeliculasVistasPorUsuario(@Param("userId") Integer userId);

    @Query("SELECT COUNT(m) FROM User u JOIN u.favorites m WHERE u.id = :userId")
    long contarPeliculasFavoritas(@Param("userId") Integer userId);

    @Query("SELECT SUM(m.runtime) FROM Seen s JOIN s.movie m WHERE s.user.id = :userId")
    Long calcularTiempoTotalVisto(@Param("userId") Integer userId);

    @Query("SELECT g.name, COUNT(g) FROM Seen s " +
            "JOIN s.movie m " +
            "JOIN m.genres g " +
            "WHERE s.user.id = :userId " +
            "GROUP BY g.name " +
            "ORDER BY COUNT(g) DESC " +
            "LIMIT 3")
    List<Object[]> obtenerGenerosMasVistos(@Param("userId") Integer userId);

    @Query("SELECT COUNT(w) FROM User u JOIN u.watchlist w WHERE u.id = :userId")
    long contarPeliculasPendientes(@Param("userId") Integer userId);

    @Query("SELECT AVG(m.runtime) FROM Seen s JOIN s.movie m WHERE s.user.id = :userId")
    Double obtenerDuracionPromedioVistas(@Param("userId") Integer userId);

    @Query("SELECT AVG(m.voteAverage) FROM Seen s JOIN s.movie m WHERE s.user.id = :userId")
    Double obtenerPuntuacionPromedioPeliculas(@Param("userId") Integer userId);

    @Query("SELECT YEAR(m.releaseDate) as year, COUNT(m) as movie_count " +
            "FROM Seen s " +
            "JOIN s.movie m " +
            "WHERE s.user.id = :userId " +
            "GROUP BY YEAR(m.releaseDate) " +
            "ORDER BY movie_count DESC " +
            "LIMIT 3")
    List<Object[]> obtenerTopPeliculasPorAnio(@Param("userId") Integer userId);

    @Query("SELECT a.name, COUNT(DISTINCT m.id) as movie_count " +
            "FROM Seen s " +
            "JOIN s.movie m " +
            "JOIN MovieCast mc ON mc.movie = m " +
            "JOIN mc.actor a " +
            "WHERE s.user.id = :userId " +
            "GROUP BY a.name " +
            "ORDER BY movie_count DESC " +
            "LIMIT 3")
    List<Object[]> obtenerTopPeliculasPorActor(@Param("userId") Integer userId);

}

