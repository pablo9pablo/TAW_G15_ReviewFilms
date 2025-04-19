package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ControllerBuscarYFiltrar {

    @Autowired
    protected MovieRepository movieRepository;

    @Autowired
    protected GenreRepository genreRepository;


    @PostMapping("/buscar")
    public String doBuscar(@RequestParam("busqueda") String busqueda, Model model) {

        List<Movie> movieListBusqueda = this.movieRepository.buscarPorTitulo(busqueda);
        model.addAttribute("movieList", movieListBusqueda);

        return "index";

    }

    @PostMapping("/filtrar")
    public String doFiltrar(@RequestParam("year") Integer year,
                            @RequestParam("rating") Integer rating,
                            @RequestParam("genre") String genre,
                            Model model) {

        List<Movie>movieList=this.movieRepository.findAll();
        List<Genre>genreList=this.genreRepository.findAll();

        model.addAttribute("genreList",genreList);
        model.addAttribute("movieList",movieList);

        return "index";

    }

}


