package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.SeenRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.*;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Busqueda;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Filtro;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Vista;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
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

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @GetMapping("/watched")
    public String doListarWatched(Model model) {

        List<Seen> seenMovies = this.seenRepository.findAll();
        model.addAttribute("seenMovies", seenMovies);

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

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
        model.addAttribute("id",id);
        model.addAttribute("vista",new Vista());

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
         *----------------------------QUITAR COMO VISTA----------------------------------------------------------*
         */

    @PostMapping("/quitarComoVista")
    public String quitarDeVistas(@ModelAttribute("vista") Vista vista, Principal principal) {
        String email = principal.getName();
        User user = usuarioRepository.findByEmail(email);

        Seen seen = seenRepository.findByUserIdAndMovieId(user.getId(), vista.getIdMovie());

        if (seen != null) {
            seenRepository.delete(seen);
        }

        return "redirect:/watched";
    }


    /*
     *----------------------------------FILTRADO--------------------------------------------------------*
     */

    protected String listarPeliculasVistasConFiltrado(Filtro filtroSeen, Model model, String orden) {

        if (filtroSeen == null) {
            filtroSeen = new Filtro();
        }
        if (filtroSeen.getGeneroIds() == null) {
            filtroSeen.setGeneroIds(new ArrayList<>());
        }

        List<Seen> seenMovies;
        LocalDate startDate = null;
        LocalDate endDate = null;

        if (filtroSeen.getYear() != null) {
            startDate = getStartDateOfYear(filtroSeen.getYear());
            endDate = getEndDateOfYear(filtroSeen.getYear());
        }

        List<Integer> generos = filtroSeen.getGeneroIds();
        if (generos != null && generos.isEmpty()) {
            generos = null;
        }

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

        if (generos == null) {
            if ("asc".equals(orden)) {
                seenMovies = this.seenRepository.buscarPorFiltrosSinGeneroOrdenAsc(
                        filtroSeen.getYear(), filtroSeen.getVote(), startDate, endDate
                );
            } else if ("desc".equals(orden)) {
                seenMovies = this.seenRepository.buscarPorFiltrosSinGeneroOrdenDesc(
                        filtroSeen.getYear(), filtroSeen.getVote(), startDate, endDate
                );
            } else {
                seenMovies = this.seenRepository.buscarPorFiltrosSinGenero(
                        filtroSeen.getYear(), filtroSeen.getVote(), startDate, endDate
                );
            }
        } else {
            if ("asc".equals(orden)) {
                seenMovies = this.seenRepository.buscarPorFiltrosConGeneroOrdenAsc(
                        filtroSeen.getYear(), filtroSeen.getVote(), generos, startDate, endDate   // <-- USAR generos!
                );
            } else if ("desc".equals(orden)) {
                seenMovies = this.seenRepository.buscarPorFiltrosConGeneroOrdenDesc(
                        filtroSeen.getYear(), filtroSeen.getVote(), generos, startDate, endDate
                );
            } else {
                seenMovies = this.seenRepository.buscarPorFiltrosConGenero(
                        filtroSeen.getYear(), filtroSeen.getVote(), generos, startDate, endDate
                );
            }
        }

        model.addAttribute("seenMovies", seenMovies);
        model.addAttribute("filtroSeen", filtroSeen);

        return "watched";
    }



    @PostMapping("/filtrarSeen")
    public String doFiltrar(@ModelAttribute("filtroSeen") Filtro filtroSeen, Model model) {
        return this.listarPeliculasVistasConFiltrado(filtroSeen, model, null);
    }

    @PostMapping("/asc")
    public String doFiltrarAsc(@ModelAttribute("filtroSeen") Filtro filtroSeen, Model model) {
        return this.listarPeliculasVistasConFiltrado(filtroSeen, model, "asc");
    }

    @PostMapping("/desc")
    public String doFiltrarDesc(@ModelAttribute("filtroSeen") Filtro filtroSeen, Model model) {
        return this.listarPeliculasVistasConFiltrado(filtroSeen, model, "desc");
    }



    public LocalDate getStartDateOfYear(int year) {
        return LocalDate.of(year, Month.JANUARY, 1);
    }


    public LocalDate getEndDateOfYear(int year) {
        return LocalDate.of(year, Month.DECEMBER, 31);
    }


}
