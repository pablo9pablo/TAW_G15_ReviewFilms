// LUCIA ROSALES SANTIAGO: 8%

package es.uma.demoservice2025.trabajo_grupo_15_taw.mapper;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class MovieMapper {

    // Convierte Movie → MovieDTO
    public static MovieDTO toDTO(Movie movie) {
        if (movie == null) return null;

        MovieDTO dto = new MovieDTO();

        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setBudget(movie.getBudget());
        dto.setHomepage(movie.getHomepage());
        dto.setOriginalLanguage(movie.getOriginalLanguage());
        dto.setOriginalTitle(movie.getOriginalTitle());
        dto.setOverview(movie.getOverview());
        dto.setPopularity(movie.getPopularity());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setRevenue(movie.getRevenue());
        dto.setRuntime(movie.getRuntime());
        dto.setStatus(movie.getStatus());
        dto.setTagline(movie.getTagline());
        dto.setVoteAverage(movie.getVoteAverage());
        dto.setVoteCount(movie.getVoteCount());
        dto.setImageUrl(movie.getImageUrl());

        // Relación con géneros
        if (movie.getGenres() != null) {
            dto.setGenreIds(
                    movie.getGenres().stream()
                            .map(Genre::getId)
                            .collect(Collectors.toList())
            );
        }

        if (movie.getCrews() != null) {
            dto.setCrewIds(movie.getCrews().stream()
                    .map(Crew::getId)
                    .collect(Collectors.toList()));
        }

        // Relación con productoras
        if (movie.getProductionCompanies() != null) {
            dto.setProductionCompanyIds(
                    movie.getProductionCompanies().stream()
                            .map(ProductionCompany::getId)
                            .collect(Collectors.toList())
            );
        }

        // Relación con actores (desde MovieCast)
        if (movie.getMovieCasts() != null) {
            dto.setActorIds(
                    movie.getMovieCasts().stream()
                            .map(MovieCast::getActor)
                            .map(Actor::getId)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public static void updateEntity(Movie movie, MovieDTO dto,
                                    List<Genre> genres,
                                    List<ProductionCompany> companies) {
        if (movie == null || dto == null) return;

        movie.setTitle(dto.getTitle());
        movie.setOriginalTitle(dto.getOriginalTitle());
        movie.setBudget(dto.getBudget());
        movie.setHomepage(dto.getHomepage());
        movie.setOriginalLanguage(dto.getOriginalLanguage());
        movie.setOverview(dto.getOverview());
        movie.setPopularity(dto.getPopularity());
        movie.setReleaseDate(dto.getReleaseDate());
        movie.setRevenue(dto.getRevenue());
        movie.setRuntime(dto.getRuntime());
        movie.setStatus(dto.getStatus());
        movie.setTagline(dto.getTagline());

        if (dto.getVoteAverage() != null) {
            movie.setVoteAverage(dto.getVoteAverage());
        }
        if (dto.getVoteCount() != null) {
            movie.setVoteCount(dto.getVoteCount());
        }

        movie.setImageUrl(dto.getImageUrl());

        // Cambiar de Set.copyOf() a HashSet
        movie.setGenres(new HashSet<>(genres));  // Aquí cambiamos
        movie.setProductionCompanies(new HashSet<>(companies));  // Y aquí también

        // Relación MovieCast se maneja por separado
    }

}
