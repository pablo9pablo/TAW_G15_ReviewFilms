package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * Método de prueba que devuelve texto plano
     */
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Esta es una prueba";
    }
}