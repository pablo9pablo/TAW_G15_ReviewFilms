package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
        List<Genre>genreList=this.genreRepository.findAll();

        model.addAttribute("genreList",genreList);
        model.addAttribute("movieList", movieListBusqueda);
        model.addAttribute("busqueda", busqueda);

        return "index";
    }


    protected String listarPeliculasConFiltrado(Filtro filtro, Model model) {

        List<Movie>movies;

        LocalDate startDate = null;
        LocalDate endDate = null;

        if (filtro.getYear() != null) {
            startDate = getStartDateOfYear(filtro.getYear());
            endDate = getEndDateOfYear(filtro.getYear());
        }

        List<Genre>genreList=this.genreRepository.findAll();
        model.addAttribute("genreList",genreList);

        if (filtro == null) {
            filtro = new Filtro();
            movies = movieRepository.findAll();

        } else if (filtro.getGeneroIds().isEmpty()) {
            movies = this.movieRepository.buscarPorFiltrosSinGenero(filtro.getYear(), filtro.getVote(), startDate, endDate);
        } else {
            movies = this.movieRepository.buscarPorFiltrosConGenero(filtro.getYear(), filtro.getVote(),filtro.getGeneroIds(),startDate,endDate);
        }
        model.addAttribute("movieList",movies);
        model.addAttribute("filtro", filtro);

        return "index";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") Filtro filtro, Model model) {

        return this.listarPeliculasConFiltrado(filtro,model);
    }


    public LocalDate getStartDateOfYear(int year) {
        return LocalDate.of(year, Month.JANUARY, 1);
    }


    public LocalDate getEndDateOfYear(int year) {
        return LocalDate.of(year, Month.DECEMBER, 31);
    }

}


