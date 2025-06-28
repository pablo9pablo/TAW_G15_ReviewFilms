// LUCIA ROSALES SANTIAGO: 3%
// MIGUEL LABELLA RAMÍREZ: 25%
// MANUEL GALÁN ALFARO: 10%

package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieCastDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ReviewDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.UserPrincipal;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.MovieMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.MovieDetailsResult;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.MovieService;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.Recomendaciones;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.UserMovieInteractionService;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Busqueda;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.BusquedaFiltro;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Filtro;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final UserMovieInteractionService userMovieInteractionService;

    public MovieController(MovieService movieService,
                           UserMovieInteractionService userMovieInteractionService) {
        this.movieService = movieService;
        this.userMovieInteractionService = userMovieInteractionService;
    }

    @GetMapping("/")
    public String index(Model model) {
        Recomendaciones recomendaciones = movieService.getRecomendaciones();

        model.addAttribute("busquedaFiltro", new BusquedaFiltro());
        model.addAttribute("movieList", recomendaciones.movieList);
        model.addAttribute("bestRatingMovieList", recomendaciones.bestRatingMovieList);
        model.addAttribute("moreCommentedMovieList", recomendaciones.moreCommentedMovieList);
        model.addAttribute("blockbusters", recomendaciones.blockbusters);
        model.addAttribute("releasesTenYearsMovieList", recomendaciones.releasesTenYearsMovieList);
        model.addAttribute("superheroeMovieList", recomendaciones.superheroeMovieList);
        model.addAttribute("dramaMovieList", recomendaciones.dramaMovieList);
        model.addAttribute("comedyMovieList", recomendaciones.comedyMovieList);
        model.addAttribute("warMovieList", recomendaciones.warMovieList);
        model.addAttribute("basedTrueStoryMovieList", recomendaciones.basedTrueStoryMovieList);
        model.addAttribute("genreList", recomendaciones.genreList);

        model.addAttribute("filtro", new Filtro());
        model.addAttribute("busqueda", new Busqueda());

        model.addAttribute("tituloCarrusel", "Todas las películas");

        return "index";
    }

    @GetMapping("/viewmovie")
    public String verPelicula(@RequestParam("id") Integer id,
                              @AuthenticationPrincipal UserPrincipal userPrincipal,
                              Model model) {
        MovieDetailsResult movieDetails = movieService.getMovieDetails(id, userPrincipal.getUser());

        if (movieDetails == null) {
            return "redirect:/";
        }

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setMovieDTO(MovieMapper.toDTO(movieDetails.movie));

        model.addAttribute("movie", movieDetails.movie);
        model.addAttribute("relatedMoviesGenre", movieDetails.relatedMoviesGenre);
        model.addAttribute("relatedMoviesProductionCompany", movieDetails.relatedMoviesProductionCompany);
        model.addAttribute("review", reviewDTO);
        model.addAttribute("hasReview", movieDetails.hasReview);

        return "VerPelicula";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editmovie")
    public String editarMovie(@RequestParam(value = "id", defaultValue = "-1") Integer id,
                              Model model) {

        boolean isNew = (id == -1);
        Movie movie = movieService.getMovieOrNew(id);
        MovieDTO movieDTO = movieService.getMovieDTO(movie, isNew);

        model.addAttribute("movie", movie);
        model.addAttribute("MovieDTO", movieDTO);
        model.addAttribute("genre", movieService.getAllGenres());
        model.addAttribute("pcompany", movieService.getAllProductionCompanies());
        model.addAttribute("crew", movieService.getAllCrew());
        model.addAttribute("actores", movieService.getAllActors());
        model.addAttribute("movieCastDTO", new MovieCastDTO());

        return "editarPelicula";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/savemovie")
    public String guardarPelicula(@ModelAttribute MovieDTO movieDTO,
                                  @RequestParam(value = "action", required = false) String action) {
        Integer movieId = movieService.saveMovie(movieDTO);

        if ("save_and_cast".equals(action)) {
            return "redirect:/cast?id=" + movieId;
        } else {
            return "redirect:/viewmovie?id=" + movieId;
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deletemovie")
    public String eliminarPelicula(@RequestParam("id") Integer movieId) {
        movieService.deleteMovieById(movieId);
        return "redirect:/";
    }

    @GetMapping("/movies/cast")
    public String verReparto(@RequestParam("id") Integer movieId, Model model) {
        movieService.cargarReparto(movieId, model);
        return "cast";
    }

    @GetMapping("/movies/crew")
    public String verEquipoTecnico(@RequestParam("id") Integer movieId, Model model) {
        movieService.cargarEquipoTecnico(movieId, model);
        return "listarCrew";
    }

    @GetMapping("/movieGenres")
    public String listarGeneros(@RequestParam Integer id, Model model) {
        movieService.cargarGeneros(id, model);
        return "movieGenre";
    }

    // ============================================================================
    // GESTIÓN DE CAST
    // ============================================================================

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cast")
    public String castPelicula(@RequestParam("id") Integer movieId, Model model) {
        try {
            movieService.prepararCast(movieId, model);
            return "editarCast";
        } catch (RuntimeException e) {
            return "redirect:/";
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveCast")
    public String saveCast(@ModelAttribute MovieCastDTO movieCastDTO) {
        try {
            return movieService.guardarCast(movieCastDTO);
        } catch (RuntimeException e) {
            return "redirect:/cast?id=" + movieCastDTO.getMovieId();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleteCast")
    public String deleteCast(@RequestParam("movieId") Integer movieId,
                             @RequestParam("actorId") Integer actorId) {
        return movieService.eliminarCast(movieId, actorId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cast/edit")
    public String editCast(@RequestParam("movieId") Integer movieId,
                           @RequestParam("actorId") Integer actorId,
                           Model model) {
        try {
            movieService.prepararEdicionCast(movieId, actorId, model);
            return "editCast";
        } catch (RuntimeException e) {
            return "redirect:/cast?id=" + movieId;
        }
    }

    // ============================================================================
    // INTERACCIONES DEL USUARIO CON PELÍCULAS
    // ============================================================================

    @PostMapping("/marcarComoVista")
    public String addToWatched(@RequestParam("idMovie") Integer idMovie,
                               HttpSession session, Principal principal) {
        String error = userMovieInteractionService.markAsWatched(idMovie, principal.getName());

        if (error != null) {
            session.setAttribute("error", error);
        }

        return "redirect:/viewmovie?id=" + idMovie;
    }

    @PostMapping("/pendiente")
    public String addToPending(@RequestParam("idMovie") Integer idMovie,
                               HttpSession session, Principal principal) {

        String error = userMovieInteractionService.addToWatchlist(idMovie, principal.getName());

        if (error != null) {
            session.setAttribute("error", error);
        }

        return "redirect:/viewmovie?id=" + idMovie;
    }

    @PostMapping("/favorita")
    public String addToFavorites(@RequestParam("idMovie") Integer idMovie,
                                 HttpSession session, Principal principal) {

        String error = userMovieInteractionService.addToFavorites(idMovie, principal.getName());

        if (error != null) {
            session.setAttribute("error", error);
        }

        return "redirect:/viewmovie?id=" + idMovie;
    }

    // ============================================================================
    // BÚSQUEDA Y FILTRADO
    // ============================================================================

    @PostMapping("/buscar-filtrar")
    public String doBuscarYFiltrar(@ModelAttribute("busquedaFiltro") BusquedaFiltro busquedaFiltro,
                                   HttpSession session) {
        session.setAttribute("ultimoFiltro", busquedaFiltro);
        return "redirect:/buscar-filtrar";
    }
    @GetMapping("/buscar-filtrar")
    public String getBuscarFiltrar(Model model, HttpSession session) {
        BusquedaFiltro filtroGuardado = (BusquedaFiltro) session.getAttribute("ultimoFiltro");

        if (filtroGuardado != null) {
            model.addAttribute("busquedaFiltro", filtroGuardado);
        } else {
            model.addAttribute("busquedaFiltro", new BusquedaFiltro());
        }

        model.addAttribute("tituloCarrusel", "Películas filtradas");
        return movieService.listarPeliculasConBusquedaYFiltrado(filtroGuardado, model);
    }


}