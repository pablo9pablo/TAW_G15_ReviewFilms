package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

@Controller
public class ControllerMovie {

    @Autowired
    protected MovieRepository movieRepository;

    @Autowired
    protected GenreRepository genreRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Movie> movieList = this.movieRepository.findAll();
        model.addAttribute("movieList", movieList);
        return "index";
    }

    @GetMapping("/viewmovie")
    public String verPelicula(@RequestParam("id") Integer movieId,
                              Model model) {
        Movie movie = movieRepository.findById(movieId).orElse(null);

        if (movie == null) return "redirect:/"; //no se ha encontrado la pelicula, redirige al listar

        model.addAttribute("movie", movie);
        return "VerPelicula";
    }

    @GetMapping("/deletemovie")
    public String eliminarPelicula(@RequestParam("id") Integer movieId) {
        //FALTA: Comprobacion de q este autenticado y de q tenga rol de editor
        movieRepository.deleteById(movieId);
        return "redirect:/";
    }

//    @PostMapping("/editmovie")
//    public String editarPelicula(@RequestParam(value = "id", required = false) Integer id,
//                                 Model model,
//                                 HttpSession session) {
//
//        //FALTA: Comprobacion de q este autenticado y de q tenga rol de editor
//        Movie movie = movieRepository.findById(id).orElse(null);
//        model.addAttribute("movie", movie);
//
//        return "VerPelicula";
//    }
}
