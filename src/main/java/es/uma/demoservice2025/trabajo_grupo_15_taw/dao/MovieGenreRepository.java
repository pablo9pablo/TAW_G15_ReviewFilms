package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

// OUAIL BOUAZZA MANSOURI : 100%
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieGenre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieGenreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, MovieGenreId> {

    @Query("SELECT mg FROM MovieGenre mg WHERE mg.genre.id = :genreId")
    List<MovieGenre> findByGenreId(@Param("genreId") Integer genreId);

    @Query("SELECT mg FROM MovieGenre mg WHERE mg.movie.id = :movieId")
    List<MovieGenre> findByMovieId(@Param("movieId") Integer movieId);
}