package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.GenreDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.GenreMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class GenreController {

    private final GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping("/movieGenres")
    public String listarGeneros(Model model) {
        List<GenreDTO> dtoList = genreRepository.findAll()
                .stream()
                .map(GenreMapper::toDto)
                .toList();
        model.addAttribute("genres", dtoList);
        return "movieGenre";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/genres")
    public String listar(Model model) {
        List<Genre> list = genreRepository.findAll();
        List<GenreDTO> dtoList = list.stream()
                .map(GenreMapper::toDto)
                .toList();

        model.addAttribute("genres", dtoList);
        model.addAttribute("genreForm", new GenreDTO());
        return "genre";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editGenre")
    public String editar(@RequestParam("id") Integer id, Model model) {
        Genre entity = genreRepository.findById(id).orElse(null);
        GenreDTO dto = GenreMapper.toDto(entity);

        List<Genre> list = genreRepository.findAll();
        List<GenreDTO> dtoList = list.stream()
                .map(GenreMapper::toDto)
                .toList();

        model.addAttribute("genres", dtoList);
        model.addAttribute("genreForm", dto);
        return "genre";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleteGenre")
    public String borrar(@RequestParam("id") Integer id) {
        genreRepository.deleteById(id);
        return "redirect:/genres";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveGenre")
    public String guardar(@ModelAttribute("genreForm") GenreDTO dto) {
        Genre entity = GenreMapper.toEntity(dto);
        genreRepository.save(entity);
        return "redirect:/genres";
    }
}
