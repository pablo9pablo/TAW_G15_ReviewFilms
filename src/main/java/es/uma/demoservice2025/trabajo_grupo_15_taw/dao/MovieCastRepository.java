package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

// OUAIL BOUAZZA MANSOURI : 100%
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieCast;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieCastId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieCastRepository extends JpaRepository<MovieCast, MovieCastId> {


    List<MovieCast> findByMovie(Movie movie);

    Optional<MovieCast> findById(MovieCastId castId);

    boolean existsById(MovieCastId castId);

    void deleteById(MovieCastId castId);

    List<MovieCast> findByMovieId(Integer id);
}
