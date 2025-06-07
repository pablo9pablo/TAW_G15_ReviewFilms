package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ReviewDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.ReviewService;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.FiltroReview;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    protected ReviewService reviewService;

    private String procesarFiltrado(FiltroReview filtroReview, Model model, Integer userId) {
        List<ReviewDTO> userReviews;

        if (filtroReview == null) {
            filtroReview = new FiltroReview();
            userReviews = reviewService.getUserReviews(userId);
        } else {
            userReviews = reviewService.getUserReviews(userId, filtroReview.getFecha(), filtroReview.getVote());
        }

        model.addAttribute("reviews", userReviews);
        model.addAttribute("filtroReview", filtroReview);

        return "reviews";
    }

    @GetMapping("/reviews")
    public String mostrarReviews(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return procesarFiltrado(null, model, userPrincipal.getUser().getId());
    }

    @PostMapping("/filtrarReview")
    public String doFiltrar(@AuthenticationPrincipal UserPrincipal userPrincipal,
                            @ModelAttribute("filtroReview") FiltroReview filtroReview,
                            Model model) {
        return procesarFiltrado(filtroReview, model, userPrincipal.getUser().getId());
    }

    @PostMapping("/deleteReview")
    public String deleteReview(@RequestParam("id") Integer reviewId,
                               @AuthenticationPrincipal UserPrincipal userPrincipal) {

        reviewService.deleteReview(reviewId, userPrincipal.getUser().getId());
        return "redirect:/reviews";
    }

    @GetMapping("/editReview")
    public String mostrarFormularioEdicion(@RequestParam("id") Integer reviewId,
                                           @RequestParam(value = "origen") String origen,
                                           @AuthenticationPrincipal UserPrincipal userPrincipal,
                                           Model model) {

        ReviewDTO reviewDTO = reviewService.getReviewById(reviewId, userPrincipal.getUser().getId());

        if (reviewDTO == null) {
            return "redirect:/reviews";
        }

        model.addAttribute("review", reviewDTO);
        model.addAttribute("movie", reviewDTO.getMovieDTO());
        model.addAttribute("origen", origen);

        return "editarReview";
    }

    @PostMapping("/savereview")
    public String guardarCambios(@ModelAttribute("review") ReviewDTO reviewDTO,
                                 @RequestParam(value = "origen") String origen,
                                 @AuthenticationPrincipal UserPrincipal userPrincipal) {

        reviewService.saveReview(reviewDTO, userPrincipal.getUser().getId());

        if ("pelicula".equals(origen)) {
            return "redirect:/viewmovie?id=" + reviewDTO.getMovieDTO().getId();
        } else {
            return "redirect:/reviews";
        }
    }

}
