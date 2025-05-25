package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.ActorRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ActorDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Actor;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.ActorMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/actor")
public class ActorController {

    private final ActorRepository actorRepository;

    public ActorController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public String listar(Model model) {
        List<Actor> actores = actorRepository.findAll();
        List<ActorDTO> dtoList = actores.stream()
                .map(ActorMapper::toDto)
                .toList();

        model.addAttribute("actors", dtoList);
        model.addAttribute("actorForm", new ActorDTO());
        return "actor";
    }

    @GetMapping("/editActor")
    public String editar(@RequestParam("id") Integer id, Model model) {
        Actor actor = actorRepository.findById(id).orElse(null);
        ActorDTO dto = ActorMapper.toDto(actor);

        List<Actor> actores = actorRepository.findAll();
        List<ActorDTO> dtoList = actores.stream()
                .map(ActorMapper::toDto)
                .toList();

        model.addAttribute("actors", dtoList);
        model.addAttribute("actorForm", dto);
        return "actor";
    }

    @GetMapping("/deleteActor")
    public String borrar(@RequestParam("id") Integer id) {
        actorRepository.deleteById(id);
        return "redirect:/actor/";
    }

    @PostMapping("/saveActor")
    public String guardar(@ModelAttribute("actorForm") ActorDTO actorDTO) {
        Actor actor = ActorMapper.toEntity(actorDTO);
        actorRepository.save(actor);
        return "redirect:/actor/";
    }
}
