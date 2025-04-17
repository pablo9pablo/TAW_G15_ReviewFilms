package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.ReviewRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Review;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.time.LocalDate;

@Controller
public class ControllerReview {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UsuarioRepository userRepository;

    @PostMapping("/newreview")
    public String crearReview(@RequestParam("movieId") Integer movieId,
                              @RequestParam("rating") Integer rating,
                              @RequestParam("comment") String description) {

        Movie movie = movieRepository.findById(movieId).orElse(null);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email);

        //CONTROL DE ERROR, MOSTRAR MENSAJE DE ERROR
        if (movie == null || user == null) {
            return "redirect:/";
        }

        Review review = new Review();
        review.setMovie(movie);
        review.setUser(user);
        review.setRating(rating);
        review.setDescription(description);
        review.setDate(LocalDate.now());

        reviewRepository.save(review);

        return "redirect:/viewmovie?id=" + movieId;
    }
}
