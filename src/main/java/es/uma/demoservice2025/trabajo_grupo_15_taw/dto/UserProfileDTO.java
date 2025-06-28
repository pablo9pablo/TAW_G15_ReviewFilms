//PABLO MARTINEZ PALOP : 100%

package es.uma.demoservice2025.trabajo_grupo_15_taw.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserProfileDTO {
    private String email;
    private Long numeroPeliculasVistas;
    private Long numeroPeliculasFavoritas;
    private Long tiempoTotalVisto;
    private Long peliculasPendientes;
    private List<Object[]> generosMasVistos;
    private Double duracionPromedio;
    private Double puntuacionPromedio;
    private List<Object[]> topPeliculasPorAnio;
    private List<Object[]> topPeliculasPorActor;
}
