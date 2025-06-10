// LUCIA ROSALES SANTIAGO: 100%

package es.uma.demoservice2025.trabajo_grupo_15_taw.service;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.UserDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private String procesarCambioPassword(UserDTO userDTO, User user) {
        if (userDTO.getPasswordActual() == null || userDTO.getPasswordActual().isBlank() ||
                userDTO.getNuevaPassword() == null || userDTO.getNuevaPassword().isBlank() ||
                userDTO.getConfirmarPassword() == null || userDTO.getConfirmarPassword().isBlank()) {
            return "Para cambiar la contraseña debes rellenar todos los campos relacionados.";
        }

        if (!passwordEncoder.matches(userDTO.getPasswordActual(), user.getPasswordHash())) {
            return "La contraseña actual es incorrecta.";
        }

        if (passwordEncoder.matches(userDTO.getNuevaPassword(), user.getPasswordHash())) {
            return "La nueva contraseña debe ser diferente a la actual.";
        }

        if (!userDTO.getNuevaPassword().equals(userDTO.getConfirmarPassword())) {
            return "La nueva contraseña y su confirmación no coinciden.";
        }

        user.setPasswordHash(passwordEncoder.encode(userDTO.getNuevaPassword()));
        return "OK";
    }

    public String actualizarDatosPerfil(UserDTO userDTO, User user) {
        boolean cambios = false;

        // Actualización de Email
        if (userDTO.getEmail() != null && !userDTO.getEmail().isBlank() &&
                !userDTO.getEmail().equals(user.getEmail())) {

            if (usuarioRepository.existsByEmail(userDTO.getEmail())) {
                return "El nuevo correo ya está en uso.";
            }
            user.setEmail(userDTO.getEmail());
            cambios = true;
        }

        // Validaciones de Contraseña
        boolean quiereCambiarPass = userDTO.getPasswordActual() != null && !userDTO.getPasswordActual().isBlank() ||
                userDTO.getNuevaPassword() != null && !userDTO.getNuevaPassword().isBlank() ||
                userDTO.getConfirmarPassword() != null && !userDTO.getConfirmarPassword().isBlank();

        if (quiereCambiarPass) {
            String resultadoPass = procesarCambioPassword(userDTO, user);
            if (!"OK".equals(resultadoPass)) {
                return resultadoPass;
            }
            cambios = true;
        }

        if (!cambios) {
            return "No se realizaron cambios.";
        }

        usuarioRepository.save(user);
        return "OK";
    }
}