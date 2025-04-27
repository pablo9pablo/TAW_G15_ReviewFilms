package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Review;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Seen;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.SeenId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SeenRepository extends JpaRepository<Seen,Integer> {

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


}
