package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//OUAIL BOUAZZA MANSOURI : 100%
@Controller
public class LoginController {



    @GetMapping("/signin")
    public String login() {
        return "login";
    }


}