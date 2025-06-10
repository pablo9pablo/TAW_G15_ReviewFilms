// LUCIA ROSALES SANTIAGO: 100%

package es.uma.demoservice2025.trabajo_grupo_15_taw.mapper;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ReviewDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Review;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;

public class ReviewMapper {

    public static ReviewDTO toDTO(Review review) {
        if (review == null) return null;

        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setDescription(review.getDescription());
        dto.setRating(review.getRating());
        dto.setDate(review.getDate());
        dto.setMovieDTO(review.getMovie() != null ? MovieMapper.toDTO(review.getMovie()): null);
        dto.setUserId(review.getUser() != null ? review.getUser().getId() : null);

        return dto;
    }

    public static void updateEntity(Review review, ReviewDTO dto) {
        if (review == null || dto == null) return;

        review.setDescription(dto.getDescription());
        review.setRating(dto.getRating());
        review.setDate(java.time.LocalDate.now());
    }
}
