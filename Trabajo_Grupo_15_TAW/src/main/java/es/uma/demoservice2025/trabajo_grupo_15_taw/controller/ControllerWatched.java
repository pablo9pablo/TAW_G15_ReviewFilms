package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.SeenRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Seen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ControllerWatched {

    @Autowired
    protected MovieRepository movieRepository;

    @Autowired
    protected SeenRepository seenRepository;

    @Autowired
    protected GenreRepository genreRepository;

    @GetMapping("/watched")
    public String doListar(Model model) {

        List<Seen> seenMovies = this.seenRepository.findAll();

        model.addAttribute("seenMovies", seenMovies);
        return "watched";

    }

    @PostMapping("/filtrarSeen")
    public String doFiltrar(@RequestParam("year") Integer year,
                            @RequestParam("rating") Integer rating,
                            @RequestParam("genre") String genre,
                            Model model) {

        List<Movie>movieList=this.movieRepository.findAll();
        List<Genre>genreList=this.genreRepository.findAll();

        model.addAttribute("genreList",genreList);
        model.addAttribute("movieList",movieList);

        return "watched";

    }
}
