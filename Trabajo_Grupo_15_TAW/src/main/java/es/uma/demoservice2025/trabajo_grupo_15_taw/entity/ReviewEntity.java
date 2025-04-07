package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "review", indexes = {
        @Index(name = "MOVIE_ID", columnList = "MOVIE_ID"),
        @Index(name = "USER_ID", columnList = "USER_ID")
})
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private MovieEntity movie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity user;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RATING", nullable = false)
    private Integer rating;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

}