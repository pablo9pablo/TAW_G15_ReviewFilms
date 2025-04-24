package es.uma.demoservice2025.trabajo_grupo_15_taw.ui;

import lombok.Data;

import java.util.List;

@Data
public class Filtro {

    protected Integer year;
    protected Double vote;
    protected List<Integer>generoIds;

}
