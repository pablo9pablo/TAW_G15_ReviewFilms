package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Favorite;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.FavoriteId;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FavouritesRepository extends JpaRepository<Favorite, FavoriteId> {

    List<Favorite> findByUserId(Integer userId);

    // Para eliminar una película de la lista de favoritos del usuario
    Favorite findByUserIdAndMovieId(Integer userId, Integer movieId);

    // Filtrado con género
    @Query("SELECT f FROM Favorite f " +
            "JOIN f.movie m " +
            "JOIN MovieGenre mg ON mg.movie = m " +
            "JOIN mg.genre g " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (COALESCE(:generoIds, NULL) IS NULL OR g.id IN :generoIds)")
    List<Favorite> buscarPorFiltrosConGenero(@Param("anyo") Integer anyo,
                                             @Param("vote") Double vote,
                                             @Param("generoIds") List<Integer> generoIds,
                                             @Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate);

    // Filtrado sin género
    @Query("SELECT f FROM Favorite f " +
            "JOIN f.movie m " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote)")
    List<Favorite> buscarPorFiltrosSinGenero(@Param("anyo") Integer anyo,
                                             @Param("vote") Double vote,
                                             @Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate);

    // Orden ascendente con género
    @Query("SELECT f FROM Favorite f " +
            "JOIN f.movie m " +
            "LEFT JOIN MovieGenre mg ON mg.movie = m " +
            "LEFT JOIN mg.genre g " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (COALESCE(:generoIds, NULL) IS NULL OR g.id IN :generoIds) " +
            "ORDER BY m.title ASC")
    List<Favorite> buscarPorFiltrosConGeneroOrdenAsc(@Param("anyo") Integer anyo,
                                                     @Param("vote") Double vote,
                                                     @Param("generoIds") List<Integer> generoIds,
                                                     @Param("startDate") LocalDate startDate,
                                                     @Param("endDate") LocalDate endDate);

    // Orden descendente con género
    @Query("SELECT f FROM Favorite f " +
            "JOIN f.movie m " +
            "LEFT JOIN MovieGenre mg ON mg.movie = m " +
            "LEFT JOIN mg.genre g " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (COALESCE(:generoIds, NULL) IS NULL OR g.id IN :generoIds) " +
            "ORDER BY m.title DESC")
    List<Favorite> buscarPorFiltrosConGeneroOrdenDesc(@Param("anyo") Integer anyo,
                                                      @Param("vote") Double vote,
                                                      @Param("generoIds") List<Integer> generoIds,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);

    // Orden ascendente sin género
    @Query("SELECT f FROM Favorite f " +
            "JOIN f.movie m " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "ORDER BY m.title ASC")
    List<Favorite> buscarPorFiltrosSinGeneroOrdenAsc(@Param("anyo") Integer anyo,
                                                     @Param("vote") Double vote,
                                                     @Param("startDate") LocalDate startDate,
                                                     @Param("endDate") LocalDate endDate);

    // Orden descendente sin género
    @Query("SELECT f FROM Favorite f " +
            "JOIN f.movie m " +
            "WHERE (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "ORDER BY m.title DESC")
    List<Favorite> buscarPorFiltrosSinGeneroOrdenDesc(@Param("anyo") Integer anyo,
                                                      @Param("vote") Double vote,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);


}
