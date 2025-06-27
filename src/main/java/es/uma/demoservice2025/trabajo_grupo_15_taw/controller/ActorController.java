package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

//OUAIL BOUAZZA MANSOURI : 100%
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ActorDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.ActorService;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.FiltroActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/actor")
@PreAuthorize("hasRole('ADMIN')")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/")
    public String doListar(Model model, @ModelAttribute("filtro") FiltroActor filtro) {
        List<ActorDTO> actores = getFilteredActors(filtro);

        model.addAttribute("actors", actores);
        model.addAttribute("filtro", filtro != null ? filtro : new FiltroActor());
        return "actor";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") FiltroActor filtro, Model model) {
        return doListar(model, filtro);
    }

    @GetMapping("/edit")
    public String doEditar(@RequestParam(value = "id", required = false) Integer id, Model model) {
        ActorDTO dto = getActorForEdit(id);
        List<MovieDTO> relatedMovies = getRelatedMovies(id);
        List<MovieDTO> unrelatedMovies = getUnrelatedMovies(id);

        model.addAttribute("actorForm", dto);
        model.addAttribute("relatedMovies", relatedMovies);
        model.addAttribute("unrelatedMovies", unrelatedMovies);
        return "editActor";
    }

    @PostMapping("/save")
    public String doGuardar(@ModelAttribute("actorForm") ActorDTO dto, Model model) {
        if (actorService.isNameAlreadyTaken(dto.getName(), dto.getId())) {
            model.addAttribute("error", "Ya existe un actor con ese nombre");
            return "editActor";
        }

        actorService.saveActor(dto);
        return "redirect:/actor/";
    }

    @PostMapping("/delete")
    public String doBorrar(@RequestParam("id") Integer id) {
        actorService.deleteActor(id);
        return "redirect:/actor/";
    }

    @PostMapping("/associateMovies")
    public String associateMovies(@ModelAttribute("actorForm") ActorDTO dto) {
        if (dto.getId() != null) {
            actorService.associateMoviesToActor(dto.getId(), dto.getSelectedMovieIds());
        }
        return "redirect:/actor/edit?id=" + dto.getId();
    }

    @GetMapping("/removeMovie")
    public String removeMovieFromActor(@RequestParam("actorId") Integer actorId,
                                       @RequestParam("movieId") Integer movieId) {
        actorService.removeMovieFromActor(actorId, movieId);
        return "redirect:/actor/edit?id=" + actorId;
    }

    // Métodos privados para mejorar la legibilidad
    private List<ActorDTO> getFilteredActors(FiltroActor filtro) {
        if (isValidFilter(filtro)) {
            return actorService.findActorsByName(filtro.getNombre());
        }
        return actorService.findAllActors();
    }

    private boolean isValidFilter(FiltroActor filtro) {
        return filtro != null && filtro.getNombre() != null && !filtro.getNombre().isEmpty();
    }

    private ActorDTO getActorForEdit(Integer id) {
        if (id != null) {
            return actorService.findActorById(id).orElse(new ActorDTO());
        }
        return new ActorDTO();
    }

    private List<MovieDTO> getRelatedMovies(Integer id) {
        if (id != null) {
            return actorService.findRelatedMovies(id);
        }
        return List.of();
    }

    private List<MovieDTO> getUnrelatedMovies(Integer id) {
        if (id != null) {
            return actorService.findUnrelatedMovies(id);
        }
        return actorService.findAllMovies();
    }
}