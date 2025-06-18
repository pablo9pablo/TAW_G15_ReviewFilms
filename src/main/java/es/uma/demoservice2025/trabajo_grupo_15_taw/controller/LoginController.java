package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    /**
     * Muestra la página de login
     * @return La vista login.jsp
     */
    @GetMapping("/signin")
    public String login() {
        return "login";
    }


}