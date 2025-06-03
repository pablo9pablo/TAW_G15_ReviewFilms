package es.uma.demoservice2025.trabajo_grupo_15_taw.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductionCompanyDTO {
    private Integer id;
    private String name;
    private List<Integer> selectedMovieIds;

}
