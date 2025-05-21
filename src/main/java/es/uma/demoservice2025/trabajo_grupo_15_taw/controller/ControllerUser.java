package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.SeenRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.UserDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.UserPrincipal;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.security.Principal;
import java.util.List;

@Controller
public class ControllerUser {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SeenRepository seenRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/perfil")
    public String mostrarPerfil(Model model, Principal principal) {
        String email = principal.getName();
        User user = usuarioRepository.findByEmail(email);

        if (user != null) {
            Integer userId = user.getId();

            // Estadísticas básicas
            long numeroPeliculasVistas = seenRepository.contarPeliculasVistasPorUsuario(userId);
            long numeroPeliculasFavoritas = seenRepository.contarPeliculasFavoritas(userId);
            Long tiempoTotalVisto = seenRepository.calcularTiempoTotalVisto(userId);
            long peliculasPendientes = seenRepository.contarPeliculasPendientes(userId);
            List<Object[]> generosMasVistos = seenRepository.obtenerGenerosMasVistos(userId);

            // Estadísticas detalladas
            Double duracionPromedio = seenRepository.obtenerDuracionPromedioVistas(userId);
            Double puntuacionPromedio = seenRepository.obtenerPuntuacionPromedioPeliculas(userId);
            List<Object[]> topPeliculasPorAnio = seenRepository.obtenerTopPeliculasPorAnio(userId);
            List<Object[]> topPeliculasPorActor = seenRepository.obtenerTopPeliculasPorActor(userId);

            model.addAttribute("usuarioEmail", email);

            model.addAttribute("numeroPeliculasVistas", numeroPeliculasVistas);
            model.addAttribute("numeroPeliculasFavoritas", numeroPeliculasFavoritas);
            model.addAttribute("tiempoTotalVisto", tiempoTotalVisto);
            model.addAttribute("peliculasPendientes", peliculasPendientes);
            model.addAttribute("generosMasVistos", generosMasVistos);

            model.addAttribute("duracionPromedio", duracionPromedio);
            model.addAttribute("puntuacionPromedio", puntuacionPromedio);
            model.addAttribute("topPeliculasPorAnio", topPeliculasPorAnio);
            model.addAttribute("topPeliculasPorActor", topPeliculasPorActor);

        } else {
            model.addAttribute("error", "No se encontró información del usuario.");
        }

        return "perfilUsuario";
    }


    @GetMapping("/editarPerfil")
    public String editarPerfil(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        User user = userPrincipal.getUser();

        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        model.addAttribute("userDTO", dto);

        return "editarPerfil";
    }


    @PostMapping("/actualizar")
    public String actualizarPerfil(@ModelAttribute("userDTO") UserDTO userDTO,
                                   @AuthenticationPrincipal UserPrincipal userPrincipal,
                                   Model model) {

        User user = userPrincipal.getUser();
        String resultado = userService.actualizarDatosPerfil(userDTO, user);

        if ("OK".equals(resultado)) {
            return "redirect:/logout"; // Forzamos re-login al ser info sensible
        } else {
            model.addAttribute("error", resultado);
            userDTO.setEmail(user.getEmail());
            model.addAttribute("userDTO", userDTO);
            return "editarPerfil";
        }
    }
}
