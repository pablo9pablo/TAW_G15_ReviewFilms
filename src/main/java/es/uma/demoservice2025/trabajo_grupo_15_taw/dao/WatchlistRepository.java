package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Seen;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Watchlist;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.WatchlistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WatchlistRepository extends JpaRepository<Watchlist, WatchlistId> {

    // Devuelve las películas de la watchlist del usuario
    List<Watchlist> findByUserId(Integer userId);

    // Filtrado con género
    @Query("SELECT w FROM Watchlist w " +
            "JOIN w.movie m " +
            "JOIN MovieGenre mg ON mg.movie = m " +
            "JOIN mg.genre g " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (COALESCE(:generoIds, NULL) IS NULL OR g.id IN :generoIds)")
    List<Watchlist> buscarPorFiltrosConGenero(@Param("anyo") Integer anyo,
                                              @Param("vote") Double vote,
                                              @Param("generoIds") List<Integer> generoIds,
                                              @Param("startDate") LocalDate startDate,
                                              @Param("endDate") LocalDate endDate);

    // Filtrado sin género
    @Query("SELECT w FROM Watchlist w " +
            "JOIN w.movie m " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote)")
    List<Watchlist> buscarPorFiltrosSinGenero(@Param("anyo") Integer anyo,
                                              @Param("vote") Double vote,
                                              @Param("startDate") LocalDate startDate,
                                              @Param("endDate") LocalDate endDate);

    // Orden ascendente con género
    @Query("SELECT w FROM Watchlist w " +
            "JOIN w.movie m " +
            "LEFT JOIN MovieGenre mg ON mg.movie = m " +
            "LEFT JOIN mg.genre g " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (COALESCE(:generoIds, NULL) IS NULL OR g.id IN :generoIds) " +
            "ORDER BY m.title ASC")
    List<Watchlist> buscarPorFiltrosConGeneroOrdenAsc(@Param("anyo") Integer anyo,
                                                      @Param("vote") Double vote,
                                                      @Param("generoIds") List<Integer> generoIds,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);

    // Orden descendente con género
    @Query("SELECT w FROM Watchlist w " +
            "JOIN w.movie m " +
            "LEFT JOIN MovieGenre mg ON mg.movie = m " +
            "LEFT JOIN mg.genre g " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (COALESCE(:generoIds, NULL) IS NULL OR g.id IN :generoIds) " +
            "ORDER BY m.title DESC")
    List<Watchlist> buscarPorFiltrosConGeneroOrdenDesc(@Param("anyo") Integer anyo,
                                                       @Param("vote") Double vote,
                                                       @Param("generoIds") List<Integer> generoIds,
                                                       @Param("startDate") LocalDate startDate,
                                                       @Param("endDate") LocalDate endDate);

    // Orden ascendente sin género
    @Query("SELECT w FROM Watchlist w " +
            "JOIN w.movie m " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "ORDER BY m.title ASC")
    List<Watchlist> buscarPorFiltrosSinGeneroOrdenAsc(@Param("anyo") Integer anyo,
                                                      @Param("vote") Double vote,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);

    // Orden descendente sin género
    @Query("SELECT w FROM Watchlist w " +
            "JOIN w.movie m " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "ORDER BY m.title DESC")
    List<Watchlist> buscarPorFiltrosSinGeneroOrdenDesc(@Param("anyo") Integer anyo,
                                                       @Param("vote") Double vote,
                                                       @Param("startDate") LocalDate startDate,
                                                       @Param("endDate") LocalDate endDate);

    // Para eliminar una película de la watchlist del usuario
    Watchlist findByUserIdAndMovieId(Integer userId, Integer movieId);
}
