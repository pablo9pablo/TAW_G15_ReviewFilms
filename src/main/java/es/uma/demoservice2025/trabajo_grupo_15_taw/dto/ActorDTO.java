package es.uma.demoservice2025.trabajo_grupo_15_taw.dto;

import lombok.Data;

import java.util.List;

@Data
public class ActorDTO {

    private Integer id;
    private String name;
    private String gender;
    private List<Integer> selectedMovieIds; // Para asociar películas

}
