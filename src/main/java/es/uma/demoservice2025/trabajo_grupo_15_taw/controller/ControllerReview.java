package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.ReviewRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Review;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Controller
public class ControllerReview {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UsuarioRepository userRepository;

    @GetMapping("/reviews")
    public String mostrarReviews(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                 Model model) {
        //Recuperamos el user
        User user = userPrincipal.getUser();

        if (user == null) {
            return "redirect:/signin"; // en caso de error o sesión inactiva
        }

        // Obtengo las reviews del usuario
        List<Review> userReviews = this.reviewRepository.findByUser(user);
        model.addAttribute("reviews", userReviews);
        model.addAttribute("reviewCount", userReviews.size());

        return "reviews";
    }

    @PostMapping("/newreview")
    public String crearReview(@RequestParam("movieId") Integer movieId,
                              @RequestParam("rating") Integer rating,
                              @RequestParam("comment") String description,
                              @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Movie movie = movieRepository.findById(movieId).orElse(null);

        User user = userPrincipal.getUser();

        if (user == null) {
            return "redirect:/signin";
        }

        //mostrar mensaje de error de q no se ha encontrado la peli
        if(movie == null) {
            return "redirect:/";
        }

        Review review = new Review();
        review.setMovie(movie);
        review.setUser(user);
        review.setRating(rating);
        review.setDescription(description);
        review.setDate(LocalDate.now());

        reviewRepository.save(review);

        //Cálculo de media dinámicamente
        Set<Review> reviews = movie.getReviews();

        if (reviews != null && !reviews.isEmpty()) {
            double avg = reviews.stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0); //para obtener la media

            movie.setVoteAverage(BigDecimal.valueOf(avg)
                    .setScale(1, RoundingMode.HALF_UP)); //para que el redondeo sea de un decimal y que redondee hacia arriba
            movie.setVoteCount(reviews.size());

            movieRepository.save(movie);
        }

        return "redirect:/viewmovie?id=" + movieId;
    }



    @PostMapping("/deleteReview")
    public String deleteReview(@RequestParam("id") Integer reviewId,
                               @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Review review = reviewRepository.findById(reviewId).orElse(null);

        //Asi nos aseguramos que nadie pueda borrar una review que no sea suya aunque cambie el id manualmente en la url
        if (review != null && review.getUser().getId().equals(userPrincipal.getUser().getId())) {
            reviewRepository.deleteById(reviewId);
        }

        return "redirect:/reviews";
    }

}


