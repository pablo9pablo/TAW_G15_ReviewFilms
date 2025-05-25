package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.CrewRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.CrewDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Crew;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.CrewMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class CrewController {

    private final CrewRepository crewRepository;

    public CrewController(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/crew")
    public String listar(Model model) {
        List<Crew> list = crewRepository.findAll();
        List<CrewDTO> dtoList = list.stream()
                .map(CrewMapper::toDto)
                .toList();

        model.addAttribute("crewList", dtoList);
        model.addAttribute("crewForm", new CrewDTO());
        return "editcrew";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editCrew")
    public String editar(@RequestParam("id") Integer id, Model model) {
        Crew entity = crewRepository.findById(id).orElse(null);
        CrewDTO dto = CrewMapper.toDto(entity);

        List<Crew> list = crewRepository.findAll();
        List<CrewDTO> dtoList = list.stream()
                .map(CrewMapper::toDto)
                .toList();

        model.addAttribute("crewList", dtoList);
        model.addAttribute("crewForm", dto);
        return "editcrew";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleteCrew")
    public String borrar(@RequestParam("id") Integer id) {
        crewRepository.deleteById(id);
        return "redirect:/crew";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveCrew")
    public String guardar(@ModelAttribute("crewForm") CrewDTO dto) {
        Crew entity = CrewMapper.toEntity(dto);
        crewRepository.save(entity);
        return "redirect:/crew";
    }
}
