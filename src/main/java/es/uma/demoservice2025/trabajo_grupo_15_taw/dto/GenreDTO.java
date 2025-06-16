package es.uma.demoservice2025.trabajo_grupo_15_taw.dto;

import lombok.Data;

import java.util.List;

@Data
public class GenreDTO {
    private Integer id;
    private String name;
    private List<Integer> selectedMovieIds;

}
