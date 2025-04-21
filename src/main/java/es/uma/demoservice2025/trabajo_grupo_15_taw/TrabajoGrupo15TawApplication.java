package es.uma.demoservice2025.trabajo_grupo_15_taw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "es.uma.demoservice2025")

public class TrabajoGrupo15TawApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrabajoGrupo15TawApplication.class, args);
    }

}
