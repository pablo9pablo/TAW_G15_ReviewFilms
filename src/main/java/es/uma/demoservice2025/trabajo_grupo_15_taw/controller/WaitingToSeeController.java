// MIGUEL LABELLA RAMÍREZ: 100%
package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.WatchlistDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.WatchlistService;
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
public class WaitingToSeeController {

    @Autowired
    private WatchlistService watchlistService;

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/waiting")
    public String doListarWaitingToSee(Model model, Principal principal) {
        String email = principal.getName();

        List<WatchlistDTO> watchlistDTOList = watchlistService.filterWatchlistByUserEmailAndFiltro(email, new Filtro(), null);
        model.addAttribute("watchlistList", watchlistDTOList);

        model.addAttribute("genreList", genreRepository.findAll());
        model.addAttribute("filtroWaitingToSee", new Filtro());

        return "waitingToSee";
    }

    @PostMapping("/filtrarWaitingToSee")
    public String doFiltrar(@ModelAttribute("filtroWaitingToSee") Filtro filtroWaitingToSee, Model model, Principal principal) {
        String email = principal.getName();

        List<WatchlistDTO> watchlistDTOList = watchlistService.filterWatchlistByUserEmailAndFiltro(email, filtroWaitingToSee, null);
        model.addAttribute("watchlistList", watchlistDTOList);

        model.addAttribute("genreList", genreRepository.findAll());
        model.addAttribute("filtroWaitingToSee", filtroWaitingToSee);

        return "waitingToSee";
    }

    @PostMapping("/ascWaiting")
    public String doFiltrarAsc(@ModelAttribute("filtroWaitingToSee") Filtro filtroWaitingToSee, Model model, Principal principal) {
        String email = principal.getName();

        List<WatchlistDTO> watchlistDTOList = watchlistService.filterWatchlistByUserEmailAndFiltro(email, filtroWaitingToSee, "asc");
        model.addAttribute("watchlistList", watchlistDTOList);

        model.addAttribute("genreList", genreRepository.findAll());
        model.addAttribute("filtroWaitingToSee", filtroWaitingToSee);

        return "waitingToSee";
    }

    @PostMapping("/descWaiting")
    public String doFiltrarDesc(@ModelAttribute("filtroWaitingToSee") Filtro filtroWaitingToSee, Model model, Principal principal) {
        String email = principal.getName();

        List<WatchlistDTO> watchlistDTOList = watchlistService.filterWatchlistByUserEmailAndFiltro(email, filtroWaitingToSee, "desc");
        model.addAttribute("watchlistList", watchlistDTOList);

        model.addAttribute("genreList", genreRepository.findAll());
        model.addAttribute("filtroWaitingToSee", filtroWaitingToSee);

        return "waitingToSee";
    }

    @PostMapping("/eliminarDePendiente")
    public String eliminarDePendiente(@RequestParam("idMovie") Integer movieId, Principal principal) {
        String email = principal.getName();
        watchlistService.removeMovieFromWatchlist(email, movieId);
        return "redirect:/waiting";
    }

    @PostMapping("/marcarComoVistaDesdePendiente")
    public String marcarComoVistaDesdePendiente(@RequestParam("idMovie") Integer movieId, Principal principal) {
        String email = principal.getName();
        watchlistService.markMovieAsSeen(email, movieId);
        return "redirect:/waiting";
    }

}
