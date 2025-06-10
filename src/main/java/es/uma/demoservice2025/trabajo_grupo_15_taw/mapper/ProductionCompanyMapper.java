
package es.uma.demoservice2025.trabajo_grupo_15_taw.mapper;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ProductionCompanyDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.ProductionCompany;
import org.springframework.stereotype.Component;

@Component
public class ProductionCompanyMapper {

    public ProductionCompanyDTO toDTO(ProductionCompany entity) {
        if (entity == null) return null;

        ProductionCompanyDTO dto = new ProductionCompanyDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

}
