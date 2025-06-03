package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.ProductionCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionCompanyRepository extends JpaRepository<ProductionCompany, Integer> {
    ProductionCompany findByNameIgnoreCase(String name);

}
