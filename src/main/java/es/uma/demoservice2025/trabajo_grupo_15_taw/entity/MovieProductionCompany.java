package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "movie_production_company", schema = "tmdb4", indexes = {
        @Index(name = "PRODUCTION_COMPANY_ID", columnList = "PRODUCTION_COMPANY_ID")
})
public class MovieProductionCompany {
    @EmbeddedId
    private MovieProductionCompanyId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private Movie movie;

    @MapsId("productionCompanyId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "PRODUCTION_COMPANY_ID", nullable = false)
    private ProductionCompany productionCompany;

}