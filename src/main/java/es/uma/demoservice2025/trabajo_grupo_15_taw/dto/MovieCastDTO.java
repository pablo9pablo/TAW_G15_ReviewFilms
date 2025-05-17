package es.uma.demoservice2025.trabajo_grupo_15_taw.dto;

import lombok.Data;

@Data
public class MovieCastDTO {

    private Integer id;
    private Integer movieId;
    private Integer actorId;
    private String actorName;
    private String character;
    private String creditId;
    private Integer creditOrder;
}
