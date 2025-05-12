package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.FavouritesRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Favorite;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Seen;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
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
}
