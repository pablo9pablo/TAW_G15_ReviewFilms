package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class ControllerMovie {

    @Autowired
    protected MovieRepository movieRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<MovieEntity> movieList = this.movieRepository.findAll();
        model.addAttribute("movieList", movieList);
        return "index";
    }

    @GetMapping("/viewmovie")
    public String verPelicula(@RequestParam("id") Integer id,
                              Model model) {
        MovieEntity movie = movieRepository.findById(id).orElse(null);
        model.addAttribute("movie", movie);
        return "VerPelicula";
    }

    //FUNCIONA, PERO NECESARIO COMPLETAR
    @GetMapping("/deletemovie")
    public String eliminarPelicula(@RequestParam("id") Integer movieId) {
        movieRepository.deleteById(movieId);
        return "redirect:/";
    }

    //COMPLETAR
    @GetMapping("/editmovie")
    public String editarPelicula(@RequestParam("id") Integer id,
                                 Model model) {
        MovieEntity movie = movieRepository.findById(id).orElse(null);
        model.addAttribute("movie", movie);
        return "editmovie";
    }




}
