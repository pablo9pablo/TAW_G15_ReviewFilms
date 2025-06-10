// LUCIA ROSALES SANTIAGO: 100%

package es.uma.demoservice2025.trabajo_grupo_15_taw.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDTO {
    private Integer id;
    private String description;
    private Integer rating;
    private LocalDate date;
    private MovieDTO movieDTO;
    private Integer userId;
}
