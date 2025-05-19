package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.FavouritesRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Favorite;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
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
public class ControllerFavourites {

    @Autowired
    protected MovieRepository movieRepository;

    @Autowired
    protected FavouritesRepository favouritesRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected GenreRepository genreRepository;

    @GetMapping("/favourites")
    public String doListarFavourites(Model model, Principal principal) {

        String email = principal.getName();
        User user = usuarioRepository.findByEmail(email);

        List<Favorite> favouriteList= this.favouritesRepository.findByUserId(user.getId());
        model.addAttribute("favouriteList", favouriteList);

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

        model.addAttribute("filtroFavourite", new Filtro());

        return "favoritas";
    }

    /*
     *----------------------------QUITAR COMO FAVORITA----------------------------------------------------------*
     */

    @PostMapping("/quitarComoFavorita")
    public String quitarDeVistas(@RequestParam("idMovie") Integer idMovie, Principal principal) {
        String email = principal.getName();
        User user = usuarioRepository.findByEmail(email);

        Favorite favorite = favouritesRepository.findByUserIdAndMovieId(user.getId(), idMovie);
        favouritesRepository.delete(favorite);

        return "redirect:/favourites";
    }

    /*
     *----------------------------------FILTRADO--------------------------------------------------------*
     */

    protected String listarPeliculasFavoritasConFiltrado(Filtro filtroFavourite, Model model, String orden) {

        if (filtroFavourite == null) {
            filtroFavourite = new Filtro();
        }
        if (filtroFavourite.getGeneroIds() == null) {
            filtroFavourite.setGeneroIds(new ArrayList<>());
        }

        List<Favorite> favouriteList;
        LocalDate startDate = null;
        LocalDate endDate = null;

        if (filtroFavourite.getYear() != null) {
            startDate = getStartDateOfYear(filtroFavourite.getYear());
            endDate = getEndDateOfYear(filtroFavourite.getYear());
        }

        List<Integer> generos = filtroFavourite.getGeneroIds();
        if (generos != null && generos.isEmpty()) {
            generos = null;
        }

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

        if (generos == null) {
            if ("asc".equals(orden)) {
                favouriteList = this.favouritesRepository.buscarPorFiltrosSinGeneroOrdenAsc(
                        filtroFavourite.getYear(), filtroFavourite.getVote(), startDate, endDate
                );
            } else if ("desc".equals(orden)) {
                favouriteList = this.favouritesRepository.buscarPorFiltrosSinGeneroOrdenDesc(
                        filtroFavourite.getYear(), filtroFavourite.getVote(), startDate, endDate
                );
            } else {
                favouriteList = this.favouritesRepository.buscarPorFiltrosSinGenero(
                        filtroFavourite.getYear(), filtroFavourite.getVote(), startDate, endDate
                );
            }
        } else {
            if ("asc".equals(orden)) {
                favouriteList = this.favouritesRepository.buscarPorFiltrosConGeneroOrdenAsc(
                        filtroFavourite.getYear(), filtroFavourite.getVote(), generos, startDate, endDate   // <-- USAR generos!
                );
            } else if ("desc".equals(orden)) {
                favouriteList = this.favouritesRepository.buscarPorFiltrosConGeneroOrdenDesc(
                        filtroFavourite.getYear(), filtroFavourite.getVote(), generos, startDate, endDate
                );
            } else {
                favouriteList = this.favouritesRepository.buscarPorFiltrosConGenero(
                        filtroFavourite.getYear(), filtroFavourite.getVote(), generos, startDate, endDate
                );
            }
        }

        model.addAttribute("favouriteList", favouriteList);
        model.addAttribute("filtroFavourite", filtroFavourite);

        return "favoritas";
    }



    @PostMapping("/filtrarFavourite")
    public String doFiltrar(@ModelAttribute("filtroFavourite") Filtro filtroFavourite, Model model) {
        return this.listarPeliculasFavoritasConFiltrado(filtroFavourite, model, null);
    }

    @PostMapping("/ascFavourite")
    public String doFiltrarAsc(@ModelAttribute("filtroFavourite") Filtro filtroFavourite, Model model) {
        return this.listarPeliculasFavoritasConFiltrado(filtroFavourite, model, "asc");
    }

    @PostMapping("/descFavourite")
    public String doFiltrarDesc(@ModelAttribute("filtroFavourite") Filtro filtroFavourite, Model model) {
        return this.listarPeliculasFavoritasConFiltrado(filtroFavourite, model, "desc");
    }



    public LocalDate getStartDateOfYear(int year) {
        return LocalDate.of(year, Month.JANUARY, 1);
    }


    public LocalDate getEndDateOfYear(int year) {
        return LocalDate.of(year, Month.DECEMBER, 31);
    }


}
