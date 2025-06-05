package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.FavouritesRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.FavoriteMovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Favorite;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.FavoriteService;
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
    private FavoriteService favoriteService;

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/favourites")
    public String doListarFavourites(Model model, Principal principal) {
        String email = principal.getName();
        List<FavoriteMovieDTO> favouriteList = favoriteService.getFavoritesByUser(email);
        List<Genre> genreList = favoriteService.getAllGenres();

        model.addAttribute("favouriteList", favouriteList);
        model.addAttribute("genreList", genreList);
        model.addAttribute("filtroFavourite", new Filtro());

        return "favoritas";
    }

    @PostMapping("/quitarComoFavorita")
    public String quitarDeFavoritas(@RequestParam("idMovie") Integer idMovie, Principal principal) {
        favoriteService.removeFavoriteMovie(principal.getName(), idMovie);
        return "redirect:/favourites";
    }

    private String listarPeliculasFavoritasConFiltrado(Filtro filtroFavourite, Model model, String orden, Principal principal) {
        if (filtroFavourite == null) {
            filtroFavourite = new Filtro();
        }
        if (filtroFavourite.getGeneroIds() == null) {
            filtroFavourite.setGeneroIds(new ArrayList<>());
        }

        List<FavoriteMovieDTO> favouriteList = favoriteService.filterFavorites(principal.getName(), filtroFavourite, orden);

        List<Genre> genreList = favoriteService.getAllGenres();

        model.addAttribute("favouriteList", favouriteList);
        model.addAttribute("genreList", genreList);
        model.addAttribute("filtroFavourite", filtroFavourite);

        return "favoritas";
    }

    @PostMapping("/filtrarFavourite")
    public String doFiltrar(@ModelAttribute("filtroFavourite") Filtro filtroFavourite, Model model, Principal principal) {
        return listarPeliculasFavoritasConFiltrado(filtroFavourite, model, null, principal);
    }

    @PostMapping("/ascFavourite")
    public String doFiltrarAsc(@ModelAttribute("filtroFavourite") Filtro filtroFavourite, Model model, Principal principal) {
        String email = principal.getName();

        List<FavoriteMovieDTO> favouriteList = favoriteService.filterFavorites(email, filtroFavourite, "asc");
        model.addAttribute("favouriteList", favouriteList);

        model.addAttribute("genreList", genreRepository.findAll());
        model.addAttribute("filtroFavourite", filtroFavourite);

        return "favoritas";
    }

    @PostMapping("/descFavourite")
    public String doFiltrarDesc(@ModelAttribute("filtroFavourite") Filtro filtroFavourite, Model model, Principal principal) {
        String email = principal.getName();

        List<FavoriteMovieDTO> favouriteList = favoriteService.filterFavorites(email, filtroFavourite, "desc");
        model.addAttribute("favouriteList", favouriteList);

        model.addAttribute("genreList", genreRepository.findAll());
        model.addAttribute("filtroFavourite", filtroFavourite);

        return "favoritas";
    }


    // Métodos auxiliares para fechas si se necesitan (puedes moverlos a Service si quieres)
    public LocalDate getStartDateOfYear(int year) {
        return LocalDate.of(year, Month.JANUARY, 1);
    }

    public LocalDate getEndDateOfYear(int year) {
        return LocalDate.of(year, Month.DECEMBER, 31);
    }

}
