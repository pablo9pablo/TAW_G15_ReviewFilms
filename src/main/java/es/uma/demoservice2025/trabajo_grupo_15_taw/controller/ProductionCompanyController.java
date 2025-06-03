package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ProductionCompanyDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.ProductionCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/productionCompanies")
public class ProductionCompanyController {

    @Autowired
    protected ProductionCompanyService service;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public String doListarProductoras(Model model) {
        List<ProductionCompanyDTO> productionCompanies = service.findAll();
        model.addAttribute("productionCompanies", productionCompanies);
        return "productionCompany";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editProduction")
    public String editOrCreateForm(@RequestParam(value = "id", required = false) Integer id, Model model) {
        ProductionCompanyDTO dto = service.findById(id);

        dto.setSelectedMovieIds(new ArrayList<>());

        model.addAttribute("productionCompany", dto);

        List<MovieDTO> relatedMovies;
        List<MovieDTO> unrelatedMovies;


        if (id != null) { //Edicion
            relatedMovies = service.findMoviesByProductionCompanyId(id);
            unrelatedMovies = service.findMoviesNotRelatedToProductionCompany(id);
        } else { //Creacion
            relatedMovies = new ArrayList<>();
            unrelatedMovies = service.findAllMovies();
        }

        model.addAttribute("relatedMovies", relatedMovies);
        model.addAttribute("unrelatedMovies", unrelatedMovies);

        return "editarProductionCompany";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveProductionCompany")
    public String save(@ModelAttribute("productionCompany") ProductionCompanyDTO dto, Model model) {
        try {
            ProductionCompanyDTO savedDto = service.saveAndReturnDTO(dto);
            return "redirect:/productionCompanies/editProduction?id=" + savedDto.getId();
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("productionCompany", dto);
            return "editarProductionCompany";
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/deleteProduction")
    public String delete(@RequestParam("id") Integer id) {
        service.deleteById(id);
        return "redirect:/productionCompanies/";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/associateMoviesToProduction")
    public String associateMovies(@ModelAttribute("productionCompany") ProductionCompanyDTO dto) {
        service.associateMoviesToProductionCompany(dto.getId(), dto.getSelectedMovieIds() != null ? dto.getSelectedMovieIds() : List.of());
        return "redirect:/productionCompanies/editProduction?id=" + dto.getId();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/removeMovieFromProduction")
    public String removeMovieFromProduction(@RequestParam("productionId") Integer productionId,
                                            @RequestParam("movieId") Integer movieId) {
        service.removeMovieFromProductionCompany(productionId, movieId);
        return "redirect:/productionCompanies/editProduction?id=" + productionId;
    }

}
