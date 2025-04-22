package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
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
    public String doBuscarGet(@RequestParam("busqueda") String busqueda, Model model) {

        List<Movie> movieListBusqueda = this.movieRepository.buscarPorTitulo(busqueda);
        List<Genre>genreList=this.genreRepository.findAll();
        model.addAttribute("genreList",genreList);

        model.addAttribute("movieList", movieListBusqueda);
        model.addAttribute("busqueda", busqueda);

        return "index";
    }


    @PostMapping("/filtrar")
    public String doFiltrarGet(@RequestParam(value = "year", required = false) Integer year,
                               @RequestParam(value = "vote", required = false) Double vote,
                               @RequestParam(value = "genreId", required = false) Integer genreId,
                               Model model) {
        LocalDate startDate = null;
        LocalDate endDate = null;

        if (year != null) {
            startDate = getStartDateOfYear(year);
            endDate = getEndDateOfYear(year);
        }

        if (genreId == null) {
            List<Movie> movieListFiltros = this.movieRepository.buscarPorFiltrosSinGenero(year, vote, startDate, endDate);
            model.addAttribute("movieList", movieListFiltros);
        } else {
            List<Movie> movieListFiltros = this.movieRepository.buscarPorFiltros(year, vote, genreId, startDate, endDate);
            model.addAttribute("movieList", movieListFiltros);
        }

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);
        model.addAttribute("year", year);
        model.addAttribute("vote", vote);
        model.addAttribute("selectedGenre", genreId);  // Este es el valor que se pasa al JSP para mostrar el género seleccionado

        return "index";
    }


    public LocalDate getStartDateOfYear(int year) {
        return LocalDate.of(year, Month.JANUARY, 1);
    }


    public LocalDate getEndDateOfYear(int year) {
        return LocalDate.of(year, Month.DECEMBER, 31);
    }

}


