package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.ReviewRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Review;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.UserPrincipal;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.FiltroReview;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.ReviewUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ControllerReview {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;


    protected String procesarFiltrado(FiltroReview filtroReview,
                                      Model model,
                                      User user) {

        List<Review> userReviews;

        if(filtroReview==null){
            filtroReview = new FiltroReview();
        }
        userReviews = this.reviewRepository.filterReviews(user,filtroReview.getFecha(), filtroReview.getVote());

        model.addAttribute("reviews", userReviews);
        model.addAttribute("filtroReview", filtroReview);

        return "reviews";
    }

    @GetMapping("/reviews")
    public String mostrarReviews(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                 Model model) {
        User user = userPrincipal.getUser();
        return procesarFiltrado(null, model, user);
    }

    @PostMapping("/filtrarReview")
    public String dofiltrar(@AuthenticationPrincipal UserPrincipal userPrincipal,
                            @ModelAttribute("filtroReview") FiltroReview filtroReview,
                            Model model){
        User user = userPrincipal.getUser();
        return procesarFiltrado(filtroReview, model, user);
    }

    @PostMapping("/deleteReview")
    public String deleteReview(@RequestParam("id") Integer reviewId,
                               @AuthenticationPrincipal UserPrincipal userPrincipal) {

        User user = userPrincipal.getUser();

        Review review = reviewRepository.findById(reviewId).orElse(null);

        if (review != null && review.getUser().getId().equals(user.getId())) {
            Movie movie = review.getMovie();
            reviewRepository.deleteById(reviewId);

            if (movie.getReviews() != null) {
                movie.setVoteAverage(movie.calcularMedia());
                movie.setVoteCount(movie.getReviews() == null ? 0 : movie.getReviews().size());
                movieRepository.save(movie);
            }
        }

        return "redirect:/reviews";
    }

    @GetMapping("/editReview")
    public String mostrarFormularioEdicion(@RequestParam("id") Integer reviewId,
                                           @AuthenticationPrincipal UserPrincipal userPrincipal,
                                           Model model) {

        User user = userPrincipal.getUser();

        Review review = reviewRepository.findById(reviewId).orElse(null);

        if (review == null || !review.getUser().getId().equals(user.getId())) {
            return "redirect:/reviews";
        }

        ReviewUI reviewUI = new ReviewUI();
        reviewUI.setId(reviewId);
        reviewUI.setDescription(review.getDescription());
        reviewUI.setRating(review.getRating());
        reviewUI.setMovieId(review.getMovie().getId());

        model.addAttribute("reviewUI", reviewUI);
        model.addAttribute("movie", review.getMovie());

        return "editarReview";
    }

    @PostMapping("/savereview")
    public String guardarCambios(@ModelAttribute("reviewUI") ReviewUI reviewUI,
                                 @AuthenticationPrincipal UserPrincipal userPrincipal) {

        User user = userPrincipal.getUser();

        Review review = (reviewUI.getId() != null) ? reviewRepository.findById(reviewUI.getId()).orElse(new Review()) : new Review();
        String path;

        if (review.getId() != null) {
            path = "redirect:/reviews"; //editar review
        } else {
            Movie movie = movieRepository.findById(reviewUI.getMovieId()).orElse(null);
            review.setUser(user);
            review.setMovie(movie);
            path = "redirect:/viewmovie?id=" + movie.getId();
        }

        review.setDescription(reviewUI.getDescription());
        review.setRating(reviewUI.getRating());
        review.setDate(LocalDate.now());
        reviewRepository.save(review);

        //Cálculo de la media
        Movie movie = review.getMovie();
        BigDecimal avg = movie.calcularMedia();
        movie.setVoteAverage(avg);
        movie.setVoteCount(movie.getReviews() == null ? 0 : movie.getReviews().size());
        movieRepository.save(movie);

        return path;
    }

}


