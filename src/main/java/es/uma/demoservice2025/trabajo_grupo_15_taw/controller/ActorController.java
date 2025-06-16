package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.ActorRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ActorDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.FiltroActor;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Actor;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.ActorMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/actor")
@PreAuthorize("hasRole('ADMIN')")
public class ActorController {

    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ActorController(ActorRepository actorRepository, MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/")
    public String doListar(Model model, @ModelAttribute("filtro") FiltroActor filtro) {
        List<ActorDTO> actores;

        if (filtro == null || filtro.getNombre() == null || filtro.getNombre().isEmpty()) {
            actores = actorRepository.findAll().stream()
                    .map(ActorMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            actores = actorRepository.findByNameContainingIgnoreCase(filtro.getNombre()).stream()
                    .map(ActorMapper::toDto)
                    .collect(Collectors.toList());
        }

        model.addAttribute("actors", actores);
        model.addAttribute("filtro", filtro == null ? new FiltroActor() : filtro);
        return "actor";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") FiltroActor filtro, Model model) {
        return doListar(model, filtro);
    }

    @GetMapping("/edit")
    public String doEditar(@RequestParam(value = "id", required = false) Integer id, Model model) {
        ActorDTO dto;

        if (id != null) {
            dto = actorRepository.findById(id)
                    .map(ActorMapper::toDto)
                    .orElse(new ActorDTO());
        } else {
            dto = new ActorDTO();
        }

        // Para gestión de películas
        List<MovieDTO> relatedMovies = new ArrayList<>();
        List<MovieDTO> unrelatedMovies = new ArrayList<>();

        if (id != null) {
            relatedMovies = movieRepository.findMoviesByActorId(id).stream()
                    .map(MovieMapper::toDTO)
                    .collect(Collectors.toList());

            unrelatedMovies = movieRepository.findMoviesNotRelatedToActor(id).stream()
                    .map(MovieMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            unrelatedMovies = movieRepository.findAll().stream()
                    .map(MovieMapper::toDTO)
                    .collect(Collectors.toList());
        }

        model.addAttribute("actorForm", dto);
        model.addAttribute("relatedMovies", relatedMovies);
        model.addAttribute("unrelatedMovies", unrelatedMovies);
        return "editActor";
    }

    @PostMapping("/save")
    public String doGuardar(@ModelAttribute("actorForm") ActorDTO dto, Model model) {
        // Validación de nombre único
        Actor existing = actorRepository.findByNameIgnoreCase(dto.getName());
        if (existing != null && (dto.getId() == null || !existing.getId().equals(dto.getId()))) {
            model.addAttribute("error", "Ya existe un actor con ese nombre");
            return "editActor";
        }

        Actor actor = ActorMapper.toEntity(dto);
        actorRepository.save(actor);
        return "redirect:/actor/";
    }

    @PostMapping("/delete")
    public String doBorrar(@RequestParam("id") Integer id) {
        actorRepository.deleteById(id);
        return "redirect:/actor/";
    }

    @PostMapping("/associateMovies")
    public String associateMovies(@ModelAttribute("actorForm") ActorDTO dto) {
        if (dto.getId() != null && dto.getSelectedMovieIds() != null) {
            for (Integer movieId : dto.getSelectedMovieIds()) {
                movieRepository.associateActorToMovie(dto.getId(), movieId);
            }
        }
        return "redirect:/actor/edit?id=" + dto.getId();
    }

    @GetMapping("/removeMovie")
    public String removeMovieFromActor(@RequestParam("actorId") Integer actorId,
                                       @RequestParam("movieId") Integer movieId) {
        movieRepository.removeActorFromMovie(actorId, movieId);
        return "redirect:/actor/edit?id=" + actorId;
    }
}