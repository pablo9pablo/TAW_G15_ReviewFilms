package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.SeenRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.ProductionCompany;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Seen;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Busqueda;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ControllerWatched {

    @Autowired
    protected MovieRepository movieRepository;

    @Autowired
    protected SeenRepository seenRepository;

    @Autowired
    protected GenreRepository genreRepository;

    @GetMapping("/watched")
    public String doListarWatched(Model model) {

        List<Seen> seenMovies = this.seenRepository.findAll();
        model.addAttribute("seenMovies", seenMovies);

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

        model.addAttribute("filtroSeen", new Filtro());

        return "watched";

    }

    @GetMapping("/asc")
    public String doListarWatchedAsc(Model model) {

        List<Seen> seenMovies = this.seenRepository.buscarPorTituloAscendente();
        List<Genre> genreList = this.genreRepository.findAll();

        model.addAttribute("genreList", genreList);
        model.addAttribute("seenMovies", seenMovies);
        model.addAttribute("filtroSeen", new Filtro());

        return "watched";
    }

    @GetMapping("/desc")
    public String doListarWatchedDesc(Model model) {

        List<Seen> seenMovies = this.seenRepository.buscarPorTituloDescendente();
        List<Genre> genreList = this.genreRepository.findAll();

        model.addAttribute("genreList", genreList);
        model.addAttribute("seenMovies", seenMovies);
        model.addAttribute("filtroSeen", new Filtro());

        return "watched";
    }



    @GetMapping("/viewmovieSeen")
    public String verPelicula(@RequestParam("id") Integer id,
                              Model model) {
        Movie movie = movieRepository.findById(id).orElse(null);

        if (movie == null) {
            return "redirect:/";
        }

        // Listas y conjuntos para las películas recomendadas
        List<Genre> genres = this.movieRepository.findGenresByMovieId(id);
        Set<Movie> relatedMoviesGenre = relatedMoviesGenre(genres, id);

        List<ProductionCompany> productionCompanies = this.movieRepository.findProductionCompanyByMovieId(id);
        Set<Movie> relatedMoviesProductionCompany = relatedMoviesProductionCompany(productionCompanies, id);

        model.addAttribute("movie", movie);
        model.addAttribute("relatedMoviesGenre", relatedMoviesGenre);
        model.addAttribute("relatedMoviesProductionCompany", relatedMoviesProductionCompany);

        return "VerPeliculaWatched";

    }

    public Set<Movie> relatedMoviesGenre(List<Genre> genres, Integer id) {
        Set<Movie> relatedMoviesGenre = new HashSet<>();

        for (Genre genre : genres) {
            relatedMoviesGenre.addAll(this.movieRepository.findMoviesByGenre(genre.getId(), id));
        }
        return relatedMoviesGenre;
    }

    public Set<Movie> relatedMoviesProductionCompany(List<ProductionCompany> productionCompanies, Integer id) {
        Set<Movie> relatedMoviesProductionCompany = new HashSet<>();

        for (ProductionCompany productionCompany : productionCompanies) {
            relatedMoviesProductionCompany.addAll(this.movieRepository.findMoviesByProductionCompany(productionCompany.getId(), id));
        }
        return relatedMoviesProductionCompany;
    }

    /*
     *----------------------------------FILTRADO--------------------------------------------------------*
     */

    protected String listarPeliculasVistasConFiltrado(Filtro filtroSeen, Model model) {

        if (filtroSeen == null) {
            filtroSeen = new Filtro();
        }
        if (filtroSeen.getGeneroIds() == null) {
            filtroSeen.setGeneroIds(new ArrayList<>());
        }

        List<Seen>seenMovies;
        LocalDate startDate = null;
        LocalDate endDate = null;

        if (filtroSeen.getYear() != null) {
            startDate = getStartDateOfYear(filtroSeen.getYear());
            endDate = getEndDateOfYear(filtroSeen.getYear());
        }

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

        if (filtroSeen.getGeneroIds().isEmpty()) {
            seenMovies = this.seenRepository.buscarPorFiltrosSinGenero(
                    filtroSeen.getYear(), filtroSeen.getVote(), startDate, endDate
            );
        } else {
            seenMovies = this.seenRepository.buscarPorFiltrosConGenero(
                    filtroSeen.getYear(), filtroSeen.getVote(), filtroSeen.getGeneroIds(), startDate, endDate
            );
        }

        model.addAttribute("seenMovies", seenMovies);
        model.addAttribute("filtroSeen", filtroSeen);

        return "watched";
    }


    @PostMapping("/filtrarSeen")
    public String doFiltrar(@ModelAttribute("filtroSeen") Filtro filtroSeen, Model model) {

        return this.listarPeliculasVistasConFiltrado(filtroSeen, model);
    }


    public LocalDate getStartDateOfYear(int year) {
        return LocalDate.of(year, Month.JANUARY, 1);
    }


    public LocalDate getEndDateOfYear(int year) {
        return LocalDate.of(year, Month.DECEMBER, 31);
    }


}
