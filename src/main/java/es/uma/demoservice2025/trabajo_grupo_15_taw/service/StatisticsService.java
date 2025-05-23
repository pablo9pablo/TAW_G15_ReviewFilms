// StatisticsService.java
package es.uma.demoservice2025.trabajo_grupo_15_taw.service;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.ActorRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.StatisticsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final MovieRepository movieRepository;
    private final UsuarioRepository usuarioRepository;
    private final ActorRepository actorRepository;

    public StatisticsDTO getStatistics() {
        StatisticsDTO dto = new StatisticsDTO();

        dto.setTopRatedMovies(movieRepository.findTopRatedMovies().stream()
                .limit(3).collect(Collectors.toList()));

        dto.setWorstRatedMovies(movieRepository.findWorstRatedMovies().stream()
                .limit(3).collect(Collectors.toList()));

        dto.setMostReviewedMovies(movieRepository.findMoviesWithMostReviews().stream()
                .limit(5).collect(Collectors.toList()));

        dto.setHighestGrossingMovies(movieRepository.findHighestGrossingMovies().stream()
                .limit(5).collect(Collectors.toList()));

        dto.setMoviesByGenre(movieRepository.findMovieCountByGenre());
        dto.setTotalMovies(dto.getMoviesByGenre().stream()
                .limit(5)
                .mapToLong(item -> (Long) item[1])
                .sum());

        dto.setTopProductionCompanies(movieRepository.findTopProductionCompanies());
        dto.setTopReviewers(usuarioRepository.findTopUsersByReviews());
        dto.setTopFavorites(usuarioRepository.findTopUsersByFavorites());
        dto.setTopActors(actorRepository.findTopActors());

        return dto;
    }
}
