// OUAIL BOUAZZA MANSOURI : 100%
package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.RegisterDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    UserRegisterService userRegisterService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterDTO form, Model model) {
        if (userRegisterService.emailYaExiste(form.getEmail())) {
            model.addAttribute("error", "Ese correo ya está registrado.");
            model.addAttribute("registerDTO", form);
            return "register";
        }

        userRegisterService.register(form.getEmail(), form.getPassword());
        return "redirect:/login";
    }
}