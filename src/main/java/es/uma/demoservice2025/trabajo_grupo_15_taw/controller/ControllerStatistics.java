package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.*;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/estadisticas")
public class ControllerStatistics {

    @Autowired
    protected MovieRepository movieRepository;


    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected ActorRepository actorRepository;

    @GetMapping("")
    public String mostrarEstadisticas(Model model) {
        // Estadísticas de películas
        List<Object[]> topRatedMovies = movieRepository.findTopRatedMovies();
        model.addAttribute("topRatedMovies",
                topRatedMovies.stream()
                        .limit(3)
                        .collect(Collectors.toList()));

        List<Object[]> worstRatedMovies = movieRepository.findWorstRatedMovies();
        model.addAttribute("worstRatedMovies",
                worstRatedMovies.stream()
                        .limit(3)
                        .collect(Collectors.toList()));

        List<Object[]> mostReviewedMovies = movieRepository.findMoviesWithMostReviews();
        model.addAttribute("mostReviewedMovies",
                mostReviewedMovies.stream()
                        .limit(5)
                        .collect(Collectors.toList()));

        List<Movie> highestGrossingMovies = movieRepository.findHighestGrossingMovies();
        model.addAttribute("highestGrossingMovies",
                highestGrossingMovies.stream()
                        .limit(5)
                        .collect(Collectors.toList()));


        List<Object[]> moviesByGenre = movieRepository.findMovieCountByGenre();
        model.addAttribute("moviesByGenre", moviesByGenre);
        long totalMovies = moviesByGenre.stream()
                .limit(5)
                .mapToLong(item -> (Long) item[1])
                .sum();
        model.addAttribute("totalMovies", totalMovies);


        List<Object[]> topProductionCompanies = movieRepository.findTopProductionCompanies();
        model.addAttribute("topProductionCompanies", topProductionCompanies);

        List<Object[]> topReviewers = usuarioRepository.findTopUsersByReviews();
        model.addAttribute("topReviewers", topReviewers);

        List<Object[]> topFavorites = usuarioRepository.findTopUsersByFavorites();

        model.addAttribute("topFavorites", topFavorites);

        List<Object[]> topActors = actorRepository.findTopActors();
        model.addAttribute("topActors", topActors);




        return "estadisticas";
    }
}