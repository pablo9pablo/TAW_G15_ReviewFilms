package es.uma.demoservice2025.trabajo_grupo_15_taw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "movie_keyword", schema = "tmdb4", indexes = {
        @Index(name = "KEYWORD_ID", columnList = "KEYWORD_ID")
})
public class MovieKeyword {
    @EmbeddedId
    private MovieKeywordId id;

    @MapsId("keywordId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "KEYWORD_ID", nullable = false)
    private Keyword keyword;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private Movie movie;

}