package es.uma.demoservice2025.trabajo_grupo_15_taw.mapper;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ProductionCompanyDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.ProductionCompany;

public class ProductionCompanyMapper {

    public static ProductionCompanyDTO toDto(ProductionCompany entity) {
        if (entity == null) return null;
        ProductionCompanyDTO dto = new ProductionCompanyDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static ProductionCompany toEntity(ProductionCompanyDTO dto) {
        if (dto == null) return null;
        ProductionCompany entity = new ProductionCompany();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
