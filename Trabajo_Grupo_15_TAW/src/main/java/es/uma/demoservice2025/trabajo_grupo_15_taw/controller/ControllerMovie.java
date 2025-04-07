package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class ControllerMovie {

    @Autowired
    protected MovieRepository movieRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<MovieEntity> lista = this.movieRepository.findAll();
        model.addAttribute("lista", lista);
        return "index";
    }

}
