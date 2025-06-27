// LUCIA ROSALES SANTIAGO: 70%
// OUAIL BOUAZZA MANSOURI : 100%
package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.ProductionCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionCompanyRepository extends JpaRepository<ProductionCompany, Integer> {
    ProductionCompany findByNameIgnoreCase(String name);

    List<ProductionCompany> findByMovies_Id(Integer id);

    @Query("SELECT p FROM ProductionCompany p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<ProductionCompany> buscarProductoras(@Param("nombre") String nombre);

}
