package es.uma.demoservice2025.trabajo_grupo_15_taw.dto;

// OUAIL BOUAZZA MANSOURI : 100%
import lombok.Data;

import java.util.List;

@Data
public class CrewDTO {

    private Integer id;
    private String name;
    private String gender;
    private List<Integer> selectedMovieIds;

}
