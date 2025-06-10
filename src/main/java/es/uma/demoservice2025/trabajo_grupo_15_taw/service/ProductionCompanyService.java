// LUCIA ROSALES SANTIAGO: 100%

package es.uma.demoservice2025.trabajo_grupo_15_taw.service;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ProductionCompanyDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.ProductionCompanyRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.ProductionCompany;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.MovieMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.ProductionCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductionCompanyService {

    @Autowired
    protected ProductionCompanyRepository productionCompanyRepository;

    @Autowired
    protected ProductionCompanyMapper productionCompanyMapper;

    @Autowired
    protected MovieRepository movieRepository;

    public List<ProductionCompanyDTO> findProductionCompanies() {
        return this.findProductionCompanies(null);
    }

    public List<ProductionCompanyDTO> findProductionCompanies(String nombre) {
        List<ProductionCompany> productionCompanies;

        if(nombre == null){
            productionCompanies = this.productionCompanyRepository.findAll();
        }else{
            productionCompanies = this.productionCompanyRepository.buscarProductoras(nombre);
        }
        return productionCompanies.stream()
                .map(productionCompanyMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductionCompanyDTO findById(Integer id) {
        if (id == null) {
            return new ProductionCompanyDTO(); // DTO vacío para creación
        }

        ProductionCompany entity = productionCompanyRepository.findById(id).orElse(new ProductionCompany());
        return productionCompanyMapper.toDTO(entity);
    }



    public ProductionCompanyDTO saveAndReturnDTO(ProductionCompanyDTO dto) {
        ProductionCompany existing = productionCompanyRepository.findByNameIgnoreCase(dto.getName());

        if (existing != null && (dto.getId() == null || !existing.getId().equals(dto.getId()))) {
            throw new IllegalArgumentException("Ya existe una productora con ese nombre.");
        }

        ProductionCompany entity = dto.getId() != null
                ? productionCompanyRepository.findById(dto.getId()).orElse(new ProductionCompany())
                : new ProductionCompany();

        entity.setName(dto.getName());
        ProductionCompany saved = productionCompanyRepository.save(entity);
        return productionCompanyMapper.toDTO(saved);
    }



    public void deleteById(Integer id) {
        productionCompanyRepository.deleteById(id);
    }


    public List<MovieDTO> findMoviesByProductionCompanyId(Integer id) {
        return movieRepository.findMoviesByProductionCompany(id).stream()
                .map(MovieMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<MovieDTO> findMoviesNotRelatedToProductionCompany(Integer id) {
        List<Movie> unrelated = movieRepository.findMoviesNotRelatedToProductionCompany(id);

        return unrelated.stream()
                .map(MovieMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<MovieDTO> findAllMovies() {
        return movieRepository.findAll().stream()
                .map(MovieMapper::toDTO)
                .collect(Collectors.toList());
    }


    public void associateMoviesToProductionCompany(Integer productionCompanyId, List<Integer> movieIds) {
        ProductionCompany company = productionCompanyRepository.findById(productionCompanyId).orElse(null);
        if (company == null) return;

        List<Movie> moviesToAssociate = movieRepository.findAllById(movieIds);
        for (Movie movie : moviesToAssociate) {
            movie.getProductionCompanies().add(company);
        }
        movieRepository.saveAll(moviesToAssociate);
    }

    public void removeMovieFromProductionCompany(Integer productionCompanyId, Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        ProductionCompany company = productionCompanyRepository.findById(productionCompanyId).orElse(null);

        if (movie != null && company != null) {
            movie.getProductionCompanies().remove(company);
            movieRepository.save(movie);
        }
    }



}
