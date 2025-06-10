// LUCIA ROSALES SANTIAGO: 100%

package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    @Query("SELECT r.id FROM Review r WHERE r.user.id = :userId AND r.movie.id = :movieId")
    public Optional<Integer> findReviewIdByUserAndMovie(@Param("userId") Integer userId, @Param("movieId") Integer movieId);

    @Query("SELECT r FROM Review r WHERE r.user.id = :userId AND (:reviewDate IS NULL OR (r.date >= :reviewDate AND r.date <= CURRENT_DATE)) AND (:minRating IS NULL OR r.rating >= :minRating)")
    public List<Review> filterReviews(@Param("userId") Integer userId, @Param("reviewDate") LocalDate reviewDate, @Param("minRating") Integer minRating);

}

