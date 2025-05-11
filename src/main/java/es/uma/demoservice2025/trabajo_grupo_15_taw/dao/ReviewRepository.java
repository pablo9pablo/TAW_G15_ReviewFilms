package es.uma.demoservice2025.trabajo_grupo_15_taw.dao;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Review;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    @Query("SELECT r FROM Review r JOIN FETCH r.movie WHERE r.user = :user")
    public List<Review> findByUser(@Param("user") User user);

    @Query("SELECT r.id FROM Review r WHERE r.user.id = :userId AND r.movie.id = :movieId")
    public Optional<Integer> findReviewIdByUserAndMovie(@Param("userId") Integer userId, @Param("movieId") Integer movieId);


}

