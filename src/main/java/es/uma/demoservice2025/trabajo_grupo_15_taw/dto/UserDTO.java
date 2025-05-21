package es.uma.demoservice2025.trabajo_grupo_15_taw.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    @Email(message = "Correo inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    private String passwordActual;
    private String nuevaPassword;
    private String confirmarPassword;
}
