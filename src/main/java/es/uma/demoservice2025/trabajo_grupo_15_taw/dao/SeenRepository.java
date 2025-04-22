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

public interface SeenRepository extends JpaRepository<Seen, SeenId> {


    // Filtrado con género
    @Query("SELECT s FROM Seen s " +
            "JOIN s.movie m " +
            "JOIN MovieGenre mg ON mg.movie = m " +
            "JOIN mg.genre g " +
            "WHERE (:anyo IS NULL OR m.releaseDate BETWEEN :startDate AND :endDate) " +
            "AND (:vote IS NULL OR m.voteAverage >= :vote) " +
            "AND (:genreId IS NULL OR g.id = :genreId)")
    public List<Seen> buscarPorFiltros(@Param("anyo") Integer anyo,
                                @Param("vote") Double vote,
                                @Param("genreId") Integer genreId,
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

    public boolean existsById(SeenId seenId);



}
