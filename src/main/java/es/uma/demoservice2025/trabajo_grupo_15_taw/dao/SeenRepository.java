package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SeenRepository extends JpaRepository<Seen,SeenId> {

    // Devuelve las películas de la watchlist del usuario
    List<Seen>findByUserId(Integer userId);

    //Filtrado con genero
    @Query("SELECT s FROM Seen s " +
            "JOIN s.movie m " +
            "JOIN MovieGenre mg ON mg.movie = m " +
            "JOIN mg.genre g " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (COALESCE(:generoIds, NULL) IS NULL OR g.id IN :generoIds)")
    public List<Seen> buscarPorFiltrosConGenero(@Param("anyo") Integer anyo,
                                                @Param("vote") Double vote,
                                                @Param("generoIds") List<Integer> generoIds,
                                                @Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);


    // Filtrado sin género
    @Query("SELECT s FROM Seen s " +
            "JOIN s.movie m " +
            "WHERE (:anyo IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote)")
    public List<Seen> buscarPorFiltrosSinGenero(@Param("anyo") Integer anyo,
                                         @Param("vote") Double vote,
                                         @Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);


    // Filtrado con orden ascendente
    @Query("SELECT s FROM Seen s " +
            "JOIN s.movie m " +
            "LEFT JOIN MovieGenre mg ON mg.movie = m " +
            "LEFT JOIN mg.genre g " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (COALESCE(:generoIds, NULL) IS NULL OR g.id IN :generoIds) " +
            "ORDER BY m.title ASC")
    List<Seen> buscarPorFiltrosConGeneroOrdenAsc(@Param("anyo") Integer anyo,
                                           @Param("vote") Double vote,
                                           @Param("generoIds") List<Integer> generoIds,
                                           @Param("startDate") LocalDate startDate,
                                           @Param("endDate") LocalDate endDate);

    // Filtrado con orden descendente
    @Query("SELECT s FROM Seen s " +
            "JOIN s.movie m " +
            "LEFT JOIN MovieGenre mg ON mg.movie = m " +
            "LEFT JOIN mg.genre g " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (COALESCE(:generoIds, NULL) IS NULL OR g.id IN :generoIds)" +
            "ORDER BY m.title DESC")
    List<Seen> buscarPorFiltrosConGeneroOrdenDesc(@Param("anyo") Integer anyo,
                                            @Param("vote") Double vote,
                                            @Param("generoIds") List<Integer> generoIds,
                                            @Param("startDate") LocalDate startDate,
                                            @Param("endDate") LocalDate endDate);


    @Query("SELECT s FROM Seen s " +
            "JOIN s.movie m " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "ORDER BY m.title ASC")
    List<Seen> buscarPorFiltrosSinGeneroOrdenAsc(@Param("anyo") Integer anyo,
                                                 @Param("vote") Double vote,
                                                 @Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);

    @Query("SELECT s FROM Seen s " +
            "JOIN s.movie m " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "ORDER BY m.title DESC")
    List<Seen> buscarPorFiltrosSinGeneroOrdenDesc(@Param("anyo") Integer anyo,
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

    @Query(value = "SELECT g.name, COUNT(g) FROM Seen s " +
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

    @Query("SELECT YEAR(m.releaseDate) as year, m.title, COUNT(s) as views " +
            "FROM Seen s JOIN s.movie m " +
            "WHERE s.user.id = :userId " +
            "GROUP BY YEAR(m.releaseDate), m.title " +
            "ORDER BY YEAR(m.releaseDate) DESC, COUNT(s) DESC " +
            "LIMIT 3")
    List<Object[]> obtenerTopPeliculasPorAnio(@Param("userId") Integer userId);


    @Query("SELECT a.name, m.title, COUNT(s) as views " +
            "FROM Seen s " +
            "JOIN s.movie m " +
            "JOIN MovieCast mc ON mc.movie = m " +
            "JOIN mc.actor a " +
            "WHERE s.user.id = :userId " +
            "GROUP BY a.name, m.title " +
            "ORDER BY views DESC " +
            "LIMIT 3")
    List<Object[]> obtenerTopPeliculasPorActor(@Param("userId") Integer userId);

   }
