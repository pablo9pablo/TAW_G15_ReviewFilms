package es.uma.demoservice2025.trabajo_grupo_15_taw.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
public class FiltroReviewDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected LocalDate fecha;
    protected Integer vote;
}
