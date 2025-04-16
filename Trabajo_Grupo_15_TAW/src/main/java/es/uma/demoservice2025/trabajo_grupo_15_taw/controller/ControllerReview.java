package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.ReviewRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UserRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieEntity;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.ReviewEntity;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.UserEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ControllerReview {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/newreview")
    public String crearReview(@RequestParam("movieId") Integer movieId,
                              @RequestParam("rating") Integer rating,
                              @RequestParam("comment") String description,
                              HttpSession session) {

        MovieEntity movie = movieRepository.findById(movieId).orElse(null);

        UserEntity user = (UserEntity) session.getAttribute("user");

        ReviewEntity review = new ReviewEntity();
        review.setMovie(movie);
        review.setUser(user);
        review.setRating(rating);
        review.setDescription(description);
        review.setDate(LocalDate.now());

        reviewRepository.save(review);

        return "redirect:/";
    }

}
