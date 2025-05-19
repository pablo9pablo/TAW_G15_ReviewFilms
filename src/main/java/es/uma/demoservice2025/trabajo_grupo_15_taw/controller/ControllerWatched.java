package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.SeenRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.*;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.FiltroDTO;
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
    public String doListarWatched(Model model,Principal principal) {

        String email = principal.getName();
        User user = usuarioRepository.findByEmail(email);

        List<Seen>seenMovies = this.seenRepository.findByUserId(user.getId());
        model.addAttribute("seenMovies", seenMovies);

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

        model.addAttribute("filtroSeen", new FiltroDTO());

        return "watched";

    }

    /*
     *----------------------------QUITAR COMO VISTA----------------------------------------------------------*
     */

    @PostMapping("/quitarComoVista")
    public String quitarDeVistas(@RequestParam("idMovie") Integer idMovie, Principal principal) {
        String email = principal.getName();
        User user = usuarioRepository.findByEmail(email);

        Seen seen = seenRepository.findByUserIdAndMovieId(user.getId(), idMovie);
        seenRepository.delete(seen);

        return "redirect:/watched";
    }

    /*
     *----------------------------------FILTRADO--------------------------------------------------------*
     */

    protected String listarPeliculasVistasConFiltrado(FiltroDTO filtroSeen, Model model, String orden) {

        if (filtroSeen == null) {
            filtroSeen = new FiltroDTO();
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
    public String doFiltrar(@ModelAttribute("filtroSeen") FiltroDTO filtroSeen, Model model) {
        return this.listarPeliculasVistasConFiltrado(filtroSeen, model, null);
    }

    @PostMapping("/ascSeen")
    public String doFiltrarAsc(@ModelAttribute("filtroSeen") FiltroDTO filtroSeen, Model model) {
        return this.listarPeliculasVistasConFiltrado(filtroSeen, model, "asc");
    }

    @PostMapping("/descSeen")
    public String doFiltrarDesc(@ModelAttribute("filtroSeen") FiltroDTO filtroSeen, Model model) {
        return this.listarPeliculasVistasConFiltrado(filtroSeen, model, "desc");
    }



    public LocalDate getStartDateOfYear(int year) {
        return LocalDate.of(year, Month.JANUARY, 1);
    }


    public LocalDate getEndDateOfYear(int year) {
        return LocalDate.of(year, Month.DECEMBER, 31);
    }


}
