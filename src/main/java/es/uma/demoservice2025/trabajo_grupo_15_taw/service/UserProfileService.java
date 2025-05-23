package es.uma.demoservice2025.trabajo_grupo_15_taw.service;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.SeenRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.UserProfileDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UsuarioRepository usuarioRepository;
    private final SeenRepository seenRepository;

    public UserProfileDTO getUserProfile(String email) {
        User user = usuarioRepository.findByEmail(email);
        if (user == null) {
            return null;
        }

        UserProfileDTO dto = new UserProfileDTO();
        Integer userId = user.getId();

        dto.setEmail(email);
        dto.setNumeroPeliculasVistas(seenRepository.contarPeliculasVistasPorUsuario(userId));
        dto.setNumeroPeliculasFavoritas(seenRepository.contarPeliculasFavoritas(userId));
        dto.setTiempoTotalVisto(seenRepository.calcularTiempoTotalVisto(userId));
        dto.setPeliculasPendientes(seenRepository.contarPeliculasPendientes(userId));
        dto.setGenerosMasVistos(seenRepository.obtenerGenerosMasVistos(userId));
        dto.setDuracionPromedio(seenRepository.obtenerDuracionPromedioVistas(userId));
        dto.setPuntuacionPromedio(seenRepository.obtenerPuntuacionPromedioPeliculas(userId));
        dto.setTopPeliculasPorAnio(seenRepository.obtenerTopPeliculasPorAnio(userId));
        dto.setTopPeliculasPorActor(seenRepository.obtenerTopPeliculasPorActor(userId));

        return dto;
    }
}
