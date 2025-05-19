package es.uma.demoservice2025.trabajo_grupo_15_taw.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

        @Data
        public class FiltroDTO {

            private Integer year;
            private Double vote;
            private List<Integer> generoIds = new ArrayList<>();

        }



