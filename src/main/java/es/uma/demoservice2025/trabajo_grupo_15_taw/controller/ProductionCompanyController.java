package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.ProductionCompanyRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ProductionCompanyDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.ProductionCompany;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.ProductionCompanyMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductionCompanyController {

    private final ProductionCompanyRepository productionCompanyRepository;

    public ProductionCompanyController(ProductionCompanyRepository productionCompanyRepository) {
        this.productionCompanyRepository = productionCompanyRepository;
    }

    @GetMapping("/movieProductionCompanies")
    public String listarProductoras(Model model) {
        List<ProductionCompanyDTO> dtoList = productionCompanyRepository.findAll()
                .stream()
                .map(ProductionCompanyMapper::toDto)
                .toList();
        model.addAttribute("companies", dtoList);
        return "movieProductionCompany";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/productionCompanies")
    public String listar(Model model) {
        List<ProductionCompany> list = productionCompanyRepository.findAll();
        List<ProductionCompanyDTO> dtoList = list.stream()
                .map(ProductionCompanyMapper::toDto)
                .toList();

        model.addAttribute("companies", dtoList);
        model.addAttribute("companyForm", new ProductionCompanyDTO());
        return "productionCompany";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editProductionCompany")
    public String editar(@RequestParam("id") Integer id, Model model) {
        ProductionCompany entity = productionCompanyRepository.findById(id).orElse(null);
        ProductionCompanyDTO dto = ProductionCompanyMapper.toDto(entity);

        List<ProductionCompany> list = productionCompanyRepository.findAll();
        List<ProductionCompanyDTO> dtoList = list.stream()
                .map(ProductionCompanyMapper::toDto)
                .toList();

        model.addAttribute("companies", dtoList);
        model.addAttribute("companyForm", dto);
        return "productionCompany";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleteProductionCompany")
    public String borrar(@RequestParam("id") Integer id) {
        productionCompanyRepository.deleteById(id);
        return "redirect:/productionCompanies";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveProductionCompany")
    public String guardar(@ModelAttribute("companyForm") ProductionCompanyDTO dto) {
        ProductionCompany entity = ProductionCompanyMapper.toEntity(dto);
        productionCompanyRepository.save(entity);
        return "redirect:/productionCompanies";
    }

}
