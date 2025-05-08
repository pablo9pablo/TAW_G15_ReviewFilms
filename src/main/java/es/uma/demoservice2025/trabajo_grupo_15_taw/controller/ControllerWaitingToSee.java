package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.WatchlistRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Seen;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Watchlist;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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


    @GetMapping("/waiting")
    public String doListarWaitingToSee(Model model) {

        List<Watchlist>watchlistList=this.watchlistRepository.findAll();
        model.addAttribute("watchlistList", watchlistList);

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

        model.addAttribute("filtroWaitingToSee", new Filtro());

        return "waitingToSee";
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
