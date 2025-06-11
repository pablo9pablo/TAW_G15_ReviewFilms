// MIGUEL LABELLA RAMÍREZ: 100%
package es.uma.demoservice2025.trabajo_grupo_15_taw.ui;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

        @Data
        public class Filtro {

            private Integer year;
            private Double vote;
            private List<Integer> generoIds = new ArrayList<>();

        }



