package es.uma.demoservice2025.trabajo_grupo_15_taw.ui;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BusquedaFiltro {
    private String texto;
    private Integer year;
    private Double vote;
    private List<Integer> generoIds;

    public BusquedaFiltro() {
        this.generoIds = new ArrayList<>();
    }

    public boolean hasTexto() {
        return texto != null && !texto.trim().isEmpty();
    }

    public boolean hasYear() {
        return year != null;
    }

    public boolean hasVote() {
        return vote != null;
    }

    public boolean hasGeneros() {
        return generoIds != null && !generoIds.isEmpty();
    }


}