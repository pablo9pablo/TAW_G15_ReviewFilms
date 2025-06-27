package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;


// OUAIL BOUAZZA MANSOURI : 100%
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.GenreDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.GenreService;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.FiltroGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/genres")
@PreAuthorize("hasRole('ADMIN')")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/")
    public String doListar(Model model, @ModelAttribute("filtro") FiltroGenre filtro) {
        List<GenreDTO> genres = obtenerListaGeneros(filtro);

        model.addAttribute("genres", genres);
        model.addAttribute("filtro", filtro == null ? new FiltroGenre() : filtro);

        return "genre";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") FiltroGenre filtro, Model model) {
        return doListar(model, filtro);
    }

    @GetMapping("/edit")
    public String doEditar(@RequestParam(value = "id", required = false) Integer id, Model model) {
        GenreDTO dto = obtenerGeneroParaEdicion(id);
        List<MovieDTO> relatedMovies = genreService.findRelatedMovies(id);
        List<MovieDTO> unrelatedMovies = genreService.findUnrelatedMovies(id);

        model.addAttribute("genreForm", dto);
        model.addAttribute("relatedMovies", relatedMovies);
        model.addAttribute("unrelatedMovies", unrelatedMovies);

        return "editGenre";
    }

    @PostMapping("/save")
    public String doGuardar(@ModelAttribute("genreForm") GenreDTO dto, Model model) {
        if (genreService.existsByNameIgnoreCase(dto.getName(), dto.getId())) {
            model.addAttribute("error", "Ya existe un género con ese nombre");
            model.addAttribute("genreForm", dto);
            return doEditar(dto.getId(), model);
        }

        genreService.save(dto);
        return "redirect:/genres/";
    }

    @PostMapping("/delete")
    public String doBorrar(@RequestParam("id") Integer id) {
        genreService.deleteById(id);
        return "redirect:/genres/";
    }

    @PostMapping("/associateMovies")
    public String associateMovies(@ModelAttribute("genreForm") GenreDTO dto) {
        boolean associated = genreService.associateMoviesToGenre(dto.getId(), dto.getSelectedMovieIds());

        if (!associated) {
            return "redirect:/genres/";
        }

        return "redirect:/genres/edit?id=" + dto.getId();
    }

    @GetMapping("/removeMovie")
    public String removeMovieFromGenre(@RequestParam("genreId") Integer genreId,
                                       @RequestParam("movieId") Integer movieId) {
        genreService.removeMovieFromGenre(genreId, movieId);
        return "redirect:/genres/edit?id=" + genreId;
    }

    private List<GenreDTO> obtenerListaGeneros(FiltroGenre filtro) {
        if (filtro == null || filtro.getNombre() == null || filtro.getNombre().isEmpty()) {
            return genreService.findAll();
        } else {
            return genreService.findByNameContaining(filtro.getNombre());
        }
    }

    private GenreDTO obtenerGeneroParaEdicion(Integer id) {
        if (id != null) {
            return genreService.findById(id).orElse(new GenreDTO());
        } else {
            return new GenreDTO();
        }
    }
}