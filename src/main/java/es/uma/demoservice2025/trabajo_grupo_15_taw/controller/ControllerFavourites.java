package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerFavourites {

    @GetMapping("/favourites")
    public String doListarFavourites(Model model) {


        return "favoritas";
    }
}
