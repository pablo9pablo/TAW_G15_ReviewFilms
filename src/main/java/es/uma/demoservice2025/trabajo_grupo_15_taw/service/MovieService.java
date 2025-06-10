// LUCIA ROSALES SANTIAGO: 100%

package es.uma.demoservice2025.trabajo_grupo_15_taw.service;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public void deleteMovieById(Integer movieId) {
        movieRepository.deleteById(movieId);
    }

}
