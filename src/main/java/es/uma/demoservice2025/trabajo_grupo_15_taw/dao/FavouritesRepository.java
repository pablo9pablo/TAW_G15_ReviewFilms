// MIGUEL LABELLA RAMÍREZ: 100%

package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Favorite;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.FavoriteId;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface FavouritesRepository extends JpaRepository<Favorite, FavoriteId> {

    List<Favorite> findByUserId(Integer userId);

    Favorite findByUserIdAndMovieId(Integer userId, Integer movieId);

    // ===== FILTRADO CON GÉNERO - FILTRANDO POR USUARIO =====

    @Query("SELECT f FROM Favorite f " +
            "JOIN f.movie m " +
            "JOIN MovieGenre mg ON mg.movie = m " +
            "JOIN mg.genre g " +
            "WHERE f.user.id = :userId " +
            "AND (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (COALESCE(:generoIds, NULL) IS NULL OR g.id IN :generoIds)")
    List<Favorite> buscarPorFiltrosConGeneroParaUsuario(@Param("anyo") Integer anyo,
                                                        @Param("vote") Double vote,
                                                        @Param("generoIds") List<Integer> generoIds,
                                                        @Param("startDate") LocalDate startDate,
                                                        @Param("endDate") LocalDate endDate,
                                                        @Param("userId") Integer userId);

    @Query("SELECT f FROM Favorite f " +
            "JOIN f.movie m " +
            "LEFT JOIN MovieGenre mg ON mg.movie = m " +
            "LEFT JOIN mg.genre g " +
            "WHERE f.user.id = :userId " +
            "AND (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (COALESCE(:generoIds, NULL) IS NULL OR g.id IN :generoIds) " +
            "ORDER BY m.title ASC")
    List<Favorite> buscarPorFiltrosConGeneroOrdenAscParaUsuario(@Param("anyo") Integer anyo,
                                                                @Param("vote") Double vote,
                                                                @Param("generoIds") List<Integer> generoIds,
                                                                @Param("startDate") LocalDate startDate,
                                                                @Param("endDate") LocalDate endDate,
                                                                @Param("userId") Integer userId);

    @Query("SELECT f FROM Favorite f " +
            "JOIN f.movie m " +
            "LEFT JOIN MovieGenre mg ON mg.movie = m " +
            "LEFT JOIN mg.genre g " +
            "WHERE f.user.id = :userId " +
            "AND (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (COALESCE(:generoIds, NULL) IS NULL OR g.id IN :generoIds) " +
            "ORDER BY m.title DESC")
    List<Favorite> buscarPorFiltrosConGeneroOrdenDescParaUsuario(@Param("anyo") Integer anyo,
                                                                 @Param("vote") Double vote,
                                                                 @Param("generoIds") List<Integer> generoIds,
                                                                 @Param("startDate") LocalDate startDate,
                                                                 @Param("endDate") LocalDate endDate,
                                                                 @Param("userId") Integer userId);

    // ===== FILTRADO SIN GÉNERO - FILTRANDO POR USUARIO =====

    @Query("SELECT f FROM Favorite f " +
            "JOIN f.movie m " +
            "WHERE f.user.id = :userId " +
            "AND (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote)")
    List<Favorite> buscarPorFiltrosSinGeneroParaUsuario(@Param("anyo") Integer anyo,
                                                        @Param("vote") Double vote,
                                                        @Param("startDate") LocalDate startDate,
                                                        @Param("endDate") LocalDate endDate,
                                                        @Param("userId") Integer userId);

    @Query("SELECT f FROM Favorite f " +
            "JOIN f.movie m " +
            "WHERE f.user.id = :userId " +
            "AND (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "ORDER BY m.title ASC")
    List<Favorite> buscarPorFiltrosSinGeneroOrdenAscParaUsuario(@Param("anyo") Integer anyo,
                                                                @Param("vote") Double vote,
                                                                @Param("startDate") LocalDate startDate,
                                                                @Param("endDate") LocalDate endDate,
                                                                @Param("userId") Integer userId);

    @Query("SELECT f FROM Favorite f " +
            "JOIN f.movie m " +
            "WHERE f.user.id = :userId " +
            "AND (:startDate IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "ORDER BY m.title DESC")
    List<Favorite> buscarPorFiltrosSinGeneroOrdenDescParaUsuario(@Param("anyo") Integer anyo,
                                                                 @Param("vote") Double vote,
                                                                 @Param("startDate") LocalDate startDate,
                                                                 @Param("endDate") LocalDate endDate,
                                                                 @Param("userId") Integer userId);
}
