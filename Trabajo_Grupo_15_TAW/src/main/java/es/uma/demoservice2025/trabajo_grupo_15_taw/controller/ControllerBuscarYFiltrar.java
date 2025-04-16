package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ControllerBuscarYFiltrar {

    @Autowired
    protected MovieRepository movieRepository;


    @PostMapping("/buscar")
    public String doBuscar(@RequestParam("busqueda") String busqueda, Model model) {

        List<MovieEntity> movieList = this.movieRepository.buscarPorTitulo(busqueda);
        model.addAttribute("movieList", movieList);

        return "index";

    }

}


