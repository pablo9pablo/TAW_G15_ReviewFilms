// PABLO MARTINEZ: 100%

package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.StatisticsDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estadisticas")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("")
    public String mostrarEstadisticas(Model model) {
        StatisticsDTO statistics = statisticsService.getStatistics();

        model.addAttribute("topRatedMovies", statistics.getTopRatedMovies());
        model.addAttribute("worstRatedMovies", statistics.getWorstRatedMovies());
        model.addAttribute("mostReviewedMovies", statistics.getMostReviewedMovies());
        model.addAttribute("highestGrossingMovies", statistics.getHighestGrossingMovies());
        model.addAttribute("moviesByGenre", statistics.getMoviesByGenre());
        model.addAttribute("totalMovies", statistics.getTotalMovies());
        model.addAttribute("topProductionCompanies", statistics.getTopProductionCompanies());
        model.addAttribute("topReviewers", statistics.getTopReviewers());
        model.addAttribute("topFavorites", statistics.getTopFavorites());
        model.addAttribute("topActors", statistics.getTopActors());

        return "estadisticas";
    }
}