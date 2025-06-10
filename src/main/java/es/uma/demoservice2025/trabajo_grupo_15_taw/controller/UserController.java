// PABLO MARTINEZ PALOP : 50%
// LUCIA ROSALES SANTIAGO: 50%

package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.UserDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.UserProfileDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.UserPrincipal;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.UserProfileService;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.security.Principal;

@Controller
@RequiredArgsConstructor

public class UserController {
    @Autowired
    private UserService userService;


        private final UserProfileService userProfileService;

        @GetMapping("/perfil")
        public String mostrarPerfil(Model model, Principal principal) {
            UserProfileDTO profile = userProfileService.getUserProfile(principal.getName());

            if (profile != null) {
                model.addAttribute("usuarioEmail", profile.getEmail());
                model.addAttribute("numeroPeliculasVistas", profile.getNumeroPeliculasVistas());
                model.addAttribute("numeroPeliculasFavoritas", profile.getNumeroPeliculasFavoritas());
                model.addAttribute("tiempoTotalVisto", profile.getTiempoTotalVisto());
                model.addAttribute("peliculasPendientes", profile.getPeliculasPendientes());
                model.addAttribute("generosMasVistos", profile.getGenerosMasVistos());
                model.addAttribute("duracionPromedio", profile.getDuracionPromedio());
                model.addAttribute("puntuacionPromedio", profile.getPuntuacionPromedio());
                model.addAttribute("topPeliculasPorAnio", profile.getTopPeliculasPorAnio());
                model.addAttribute("topPeliculasPorActor", profile.getTopPeliculasPorActor());
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
