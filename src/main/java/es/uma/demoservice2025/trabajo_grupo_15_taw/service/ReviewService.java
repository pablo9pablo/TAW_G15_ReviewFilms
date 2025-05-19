package es.uma.demoservice2025.trabajo_grupo_15_taw.service;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.MovieRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.ReviewRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ReviewDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Review;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    protected ReviewRepository reviewRepository;

    @Autowired
    protected MovieRepository movieRepository;

    @Autowired
    protected UsuarioRepository userRepository;

    public List<ReviewDTO> getUserReviews(Integer userId) {
        return getUserReviews(userId, null, null);
    }

    public List<ReviewDTO> getUserReviews(Integer userId, LocalDate reviewDate, Integer minRating) {
        List<Review> reviewsEntities = reviewRepository.filterReviews(userId, reviewDate, minRating);
        return reviewsEntities.stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteReview(Integer reviewId, Integer userId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);

        if (review != null && review.getUser().getId().equals(userId)) {
            Movie movie = review.getMovie();
            reviewRepository.deleteById(reviewId);

            movie.setVoteAverage(movie.calcularMedia());
            movie.setVoteCount(movie.getReviews() == null ? 0 : movie.getReviews().size());
            movieRepository.save(movie);
        }
    }

    public ReviewDTO getReviewById(Integer reviewId, Integer userId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null && review.getUser().getId().equals(userId)) {
            return ReviewMapper.toDTO(review);
        }
        return null;
    }

    public void saveReview(ReviewDTO reviewDTO, Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        Movie movie = movieRepository.findById(reviewDTO.getMovieDTO().getId()).orElse(null);

        Review review = (reviewDTO.getId() != null)
                ? reviewRepository.findById(reviewDTO.getId()).orElse(new Review())
                : new Review();

        ReviewMapper.updateEntity(review, reviewDTO);
        review.setUser(user);
        review.setMovie(movie);
        review.setDate(LocalDate.now());

        reviewRepository.save(review);

        // Recalcular media de la película
        BigDecimal avg = movie.calcularMedia();
        movie.setVoteAverage(avg);
        movie.setVoteCount(movie.getReviews() == null ? 0 : movie.getReviews().size());
        movieRepository.save(movie);
    }
}