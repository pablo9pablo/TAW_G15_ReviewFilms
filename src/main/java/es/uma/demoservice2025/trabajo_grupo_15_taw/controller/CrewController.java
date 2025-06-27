package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

// OUAIL BOUAZZA MANSOURI : 100%
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.CrewDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.CrewService;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.FiltroCrew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/crew")
@PreAuthorize("hasRole('ADMIN')")
public class CrewController {

    private final CrewService crewService;

    @Autowired
    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }

    @GetMapping("/")
    public String doListar(Model model, @ModelAttribute("filtro") FiltroCrew filtro) {
        List<CrewDTO> crewList = obtenerListaCrew(filtro);

        model.addAttribute("crewList", crewList);
        model.addAttribute("filtro", filtro == null ? new FiltroCrew() : filtro);

        return "crew";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") FiltroCrew filtro, Model model) {
        return doListar(model, filtro);
    }

    @GetMapping("/edit")
    public String doEditar(@RequestParam(value = "id", required = false) Integer id, Model model) {
        CrewDTO dto = obtenerCrewParaEdicion(id);
        List<MovieDTO> relatedMovies = crewService.findRelatedMovies(id);
        List<MovieDTO> unrelatedMovies = crewService.findUnrelatedMovies(id);

        model.addAttribute("crewForm", dto);
        model.addAttribute("relatedMovies", relatedMovies);
        model.addAttribute("unrelatedMovies", unrelatedMovies);

        return "editCrew";
    }

    @PostMapping("/save")
    public String doGuardar(@ModelAttribute("crewForm") CrewDTO dto, Model model) {
        if (crewService.existsByNameIgnoreCase(dto.getName(), dto.getId())) {
            model.addAttribute("error", "Ya existe un miembro del crew con ese nombre");
            model.addAttribute("crewForm", dto);
            return doEditar(dto.getId(), model);
        }

        crewService.save(dto);
        return "redirect:/crew/";
    }

    @GetMapping("/removeMovie")
    public String doQuitarRelacion(@RequestParam("crewId") Integer crewId,
                                   @RequestParam("movieId") Integer movieId,
                                   Model model) {
        boolean removed = crewService.removeMovieFromCrew(crewId, movieId);

        if (!removed) {
            model.addAttribute("error", "Miembro del crew no encontrado");
        }

        return "redirect:/crew/edit?id=" + crewId;
    }

    @PostMapping("/associateMoviesToCrew")
    public String doAsociarPeliculas(@ModelAttribute("crewForm") CrewDTO dto) {
        boolean associated = crewService.associateMoviesToCrew(dto.getId(), dto.getSelectedMovieIds());

        if (!associated) {
            return "redirect:/crew/";
        }

        return "redirect:/crew/edit?id=" + dto.getId();
    }

    @PostMapping("/delete")
    public String doBorrar(@RequestParam("id") Integer id) {
        crewService.deleteById(id);
        return "redirect:/crew/";
    }

    private List<CrewDTO> obtenerListaCrew(FiltroCrew filtro) {
        if (filtro == null || filtro.getNombre() == null || filtro.getNombre().isEmpty()) {
            return crewService.findAll();
        } else {
            return crewService.findByNameContaining(filtro.getNombre());
        }
    }

    private CrewDTO obtenerCrewParaEdicion(Integer id) {
        if (id != null) {
            return crewService.findById(id).orElse(new CrewDTO());
        } else {
            return new CrewDTO();
        }
    }
}