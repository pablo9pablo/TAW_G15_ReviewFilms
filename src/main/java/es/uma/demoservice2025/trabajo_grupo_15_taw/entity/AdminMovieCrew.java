package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin_movie_crew", schema = "tmdb4")
public class AdminMovieCrew {
    @EmbeddedId
    private AdminMovieCrewId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private AdminMovie movie;

    @MapsId("crewId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CREW_ID", nullable = false)
    private AdminCrew crew;

    @Column(name = "DEPARTMENT", length = 100)
    private String department;

    @Column(name = "JOB", length = 100)
    private String job;

}