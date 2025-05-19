package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.*;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.*;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Filtro;
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
import java.util.List;


@Controller
public class ControllerWaitingToSee {

    @Autowired
    protected WatchlistRepository watchlistRepository;

    @Autowired
    protected GenreRepository genreRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected SeenRepository seenRepository;

    @Autowired
    protected MovieRepository movieRepository;


    @GetMapping("/waiting")
    public String doListarWaitingToSee(Model model, Principal principal) {
        String email = principal.getName();
        User user = usuarioRepository.findByEmail(email);

        List<Watchlist>watchlistList = this.watchlistRepository.findByUserId(user.getId());
        model.addAttribute("watchlistList", watchlistList);

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

        model.addAttribute("filtroWaitingToSee", new Filtro());

        return "waitingToSee";
    }


    /*
     *----------------------------ELIMINAR DE LA LISTA DE PENDIENTE ----------------------------------------------------------*
     */

    @PostMapping("/eliminarDePendiente")
    public String quitarDeVistas(@RequestParam("idMovie") Integer idMovie, Principal principal) {
        String email = principal.getName();
        User user = usuarioRepository.findByEmail(email);

        Watchlist watchlist = watchlistRepository.findByUserIdAndMovieId(user.getId(), idMovie);
        watchlistRepository.delete(watchlist);

        return "redirect:/waiting";
    }

    /*
     *----------------------------MARCAR COMO VISTA----------------------------------------------------------*
     */

    @PostMapping("/marcarComoVistaDesdePendiente")
    public String addToWatched(@RequestParam("idMovie") Integer idMovie, Principal principal) {
        String email = principal.getName();
        User user = usuarioRepository.findByEmail(email);

        SeenId seenId = new SeenId();
        seenId.setMovieId(idMovie);
        seenId.setUserId(user.getId());

        Movie movie = movieRepository.findById(idMovie).orElse(null);

            Seen seen = new Seen();
            seen.setMovie(movie);
            seen.setId(seenId);
            seen.setUser(user);

            seenRepository.save(seen);

        Watchlist watchlist = watchlistRepository.findByUserIdAndMovieId(user.getId(), idMovie);

        watchlistRepository.delete(watchlist);

        return "redirect:/waiting";
    }


    /*
     *----------------------------------FILTRADO--------------------------------------------------------*
     */

    protected String listarPeliculasPendientesConFiltrado(Filtro filtroWaitingToSee, Model model, String orden) {

        if (filtroWaitingToSee == null) {
            filtroWaitingToSee = new Filtro();
        }
        if (filtroWaitingToSee.getGeneroIds() == null) {
            filtroWaitingToSee.setGeneroIds(new ArrayList<>());
        }

        List<Watchlist>watchlistList;
        LocalDate startDate = null;
        LocalDate endDate = null;

        if (filtroWaitingToSee.getYear() != null) {
            startDate = getStartDateOfYear(filtroWaitingToSee.getYear());
            endDate = getEndDateOfYear(filtroWaitingToSee.getYear());
        }

        List<Integer> generos = filtroWaitingToSee.getGeneroIds();
        if (generos != null && generos.isEmpty()) {
            generos = null;
        }

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

        if (generos == null) {
            if ("asc".equals(orden)) {
                watchlistList = this.watchlistRepository.buscarPorFiltrosSinGeneroOrdenAsc(
                        filtroWaitingToSee.getYear(), filtroWaitingToSee.getVote(), startDate, endDate
                );
            } else if ("desc".equals(orden)) {
                watchlistList = this.watchlistRepository.buscarPorFiltrosSinGeneroOrdenDesc(
                        filtroWaitingToSee.getYear(), filtroWaitingToSee.getVote(), startDate, endDate
                );
            } else {
                watchlistList = this.watchlistRepository.buscarPorFiltrosSinGenero(
                        filtroWaitingToSee.getYear(), filtroWaitingToSee.getVote(), startDate, endDate
                );
            }
        } else {
            if ("asc".equals(orden)) {
                watchlistList = this.watchlistRepository.buscarPorFiltrosConGeneroOrdenAsc(
                        filtroWaitingToSee.getYear(), filtroWaitingToSee.getVote(), generos, startDate, endDate   // <-- USAR generos!
                );
            } else if ("desc".equals(orden)) {
                watchlistList = this.watchlistRepository.buscarPorFiltrosConGeneroOrdenDesc(
                        filtroWaitingToSee.getYear(), filtroWaitingToSee.getVote(), generos, startDate, endDate
                );
            } else {
                watchlistList = this.watchlistRepository.buscarPorFiltrosConGenero(
                        filtroWaitingToSee.getYear(), filtroWaitingToSee.getVote(), generos, startDate, endDate
                );
            }
        }

        model.addAttribute("watchlistList", watchlistList);
        model.addAttribute("filtroWaitingToSee", filtroWaitingToSee);

        return "waitingToSee";
    }



    @PostMapping("/filtrarWaitingToSee")
    public String doFiltrar(@ModelAttribute("filtroWaitingToSee") Filtro filtroWaitingToSee, Model model) {
        return this.listarPeliculasPendientesConFiltrado(filtroWaitingToSee, model, null);
    }

    @PostMapping("/ascWaiting")
    public String doFiltrarAsc(@ModelAttribute("filtroWaitingToSee") Filtro filtroWaitingToSee, Model model) {
        return this.listarPeliculasPendientesConFiltrado(filtroWaitingToSee, model, "asc");
    }

    @PostMapping("/descWaiting")
    public String doFiltrarDesc(@ModelAttribute("filtroWaitingToSee") Filtro filtroWaitingToSee, Model model) {
        return this.listarPeliculasPendientesConFiltrado(filtroWaitingToSee, model, "desc");
    }



    public LocalDate getStartDateOfYear(int year) {
        return LocalDate.of(year, Month.JANUARY, 1);
    }


    public LocalDate getEndDateOfYear(int year) {
        return LocalDate.of(year, Month.DECEMBER, 31);
    }

}
