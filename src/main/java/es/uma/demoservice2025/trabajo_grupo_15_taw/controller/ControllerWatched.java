package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.SeenRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.ProductionCompany;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Seen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Month;
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
    public String doListar(Model model) {

        List<Seen>seenMovies = this.seenRepository.findAll();
        List<Genre>genreList=this.genreRepository.findAll();

        model.addAttribute("genreList",genreList);
        model.addAttribute("seenMovies", seenMovies);
        return "watched";

    }

    @PostMapping("/filtrarSeen")
    public String doFiltrarGet(@RequestParam(value = "year", required = false) Integer year,
                               @RequestParam(value = "vote", required = false) Double vote,
                               @RequestParam(value = "genreId", required = false) Integer genreId,
                               Model model) {
        LocalDate startDate = null;
        LocalDate endDate = null;

        // Definir el rango de fechas si se pasa el año
        if (year != null) {
            startDate = getStartDateOfYear(year);
            endDate = getEndDateOfYear(year);
        }

        // Filtrado de películas por género si se pasa genreId
        if (genreId == null) {
            List<Seen> movieListFiltros = this.seenRepository.buscarPorFiltrosSinGenero(year, vote, startDate, endDate);
            model.addAttribute("seenMovies", movieListFiltros);
        } else {
            List<Seen> movieListFiltros = this.seenRepository.buscarPorFiltros(year, vote, genreId, startDate, endDate);
            model.addAttribute("seenMovies", movieListFiltros);
        }

        // Obtener todos los géneros para la lista del select
        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);
        model.addAttribute("year", year);
        model.addAttribute("vote", vote);
        model.addAttribute("selectedGenre", genreId);  // Pasar el genreId al JSP para mantener la opción seleccionada

        return "watched";
    }


    public LocalDate getStartDateOfYear(int year) {
        return LocalDate.of(year, Month.JANUARY, 1);
    }


    public LocalDate getEndDateOfYear(int year) {
        return LocalDate.of(year, Month.DECEMBER, 31);
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
}
