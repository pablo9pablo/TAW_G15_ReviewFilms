package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieCast;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieCastId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Repository
public interface MovieCastRepository extends JpaRepository<MovieCast, Integer> {


    List<MovieCast> findByMovie(Movie movie);

    MovieCast findById(MovieCastId castId);

    boolean existsById(MovieCastId castId);

    void deleteById(MovieCastId castId);

    List<MovieCast> findByMovieId(Integer id);
}
