package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.SeenMovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.WatchlistDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.WatchedService;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class ControllerWatched {

    @Autowired
    private WatchedService watchedService;

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/watched")
    public String doListarWatched(Model model, Principal principal) {
        String email = principal.getName();

        model.addAttribute("seenMovies", watchedService.getSeenMoviesByUser(email));
        model.addAttribute("genreList", watchedService.getAllGenres());
        model.addAttribute("filtroSeen", new Filtro());

        return "watched";
    }

    @PostMapping("/quitarComoVista")
    public String quitarDeVistas(@RequestParam("idMovie") Integer idMovie, Principal principal) {
        watchedService.removeSeenMovie(principal.getName(), idMovie);
        return "redirect:/watched";
    }

    @PostMapping("/filtrarSeen")
    public String doFiltrar(@ModelAttribute("filtroSeen") Filtro filtroSeen, Model model, Principal principal) {
        return this.cargarPeliculasConFiltro(filtroSeen, model, null, principal);
    }

    @PostMapping("/ascSeen")
    public String doFiltrarAsc(@ModelAttribute("filtroSeen") Filtro filtroSeen, Model model, Principal principal) {
        return this.cargarPeliculasConFiltro(filtroSeen, model, "asc", principal);
    }

    @PostMapping("/descSeen")
    public String doFiltrarDesc(@ModelAttribute("filtroSeen") Filtro filtroSeen, Model model, Principal principal) {
        return this.cargarPeliculasConFiltro(filtroSeen, model, "desc", principal);
    }

    private String cargarPeliculasConFiltro(Filtro filtroSeen, Model model, String orden, Principal principal) {
        String email = principal.getName();

        List<SeenMovieDTO> filteredMovies = watchedService.filterSeenMovies(email, filtroSeen, orden);

        model.addAttribute("seenMovies", filteredMovies);
        model.addAttribute("genreList", watchedService.getAllGenres());
        model.addAttribute("filtroSeen", filtroSeen);

        return "watched";
    }
}
