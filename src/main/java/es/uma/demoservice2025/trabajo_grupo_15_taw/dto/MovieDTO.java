package es.uma.demoservice2025.trabajo_grupo_15_taw.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class MovieDTO {

    private Integer id;
    private BigDecimal budget;
    private String homepage;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private BigDecimal popularity;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private BigDecimal revenue;
    private Integer runtime;
    private String status;
    private String tagline;
    private String title;
    private BigDecimal voteAverage;
    private Integer voteCount;
    private String imageUrl;

    private List<Integer> genreIds = new ArrayList<>();
    private List<Integer> productionCompanyIds = new ArrayList<>();
    private List<Integer> actorIds = new ArrayList<>();
    private List<Integer> crewIds = new ArrayList<>();
}
