package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminMenuController {

    @GetMapping("/menu")
    public String menuCrud() {
        return "adminMenu"; // nombre JSP para el menú
    }
}
