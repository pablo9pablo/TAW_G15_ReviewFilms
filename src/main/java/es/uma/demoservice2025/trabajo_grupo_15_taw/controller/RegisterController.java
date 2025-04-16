package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;


import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;

@Controller
public class RegisterController {

    @Autowired
    UserRegisterService userRegisterService;

    @GetMapping("/Register")
    public String redirigir() {
        return "register";
    }

    @PostMapping("/AddUser")
    public String registerUser(@RequestParam String email,
                             @RequestParam String password) {
        System.out.println("¡Se ejecutó AddUser con email: " + email + "!");
        userRegisterService.register(email, password);
        return "redirect:/";


    }
}
