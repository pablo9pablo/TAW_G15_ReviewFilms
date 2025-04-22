package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.RegisterDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.service.UserRegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    UserRegisterService userRegisterService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        if (!model.containsAttribute("registerDTO")) {
            model.addAttribute("registerDTO", new RegisterDTO());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("registerDTO") @Valid RegisterDTO form,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", result);
            redirectAttributes.addFlashAttribute("registerDTO", form);
            return "redirect:/register";
        }

        if (userRegisterService.emailYaExiste(form.getEmail())) {
            redirectAttributes.addFlashAttribute("error", "Ese correo ya está registrado.");
            redirectAttributes.addFlashAttribute("registerDTO", form);
            return "redirect:/register";
        }

        try {
            userRegisterService.register(form.getEmail(), form.getPassword());
        }
         catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error inesperado. Por favor, intenta de nuevo más tarde.");
            return "redirect:/register";
        }

        redirectAttributes.addFlashAttribute("success", "¡Registro exitoso! Ya puedes iniciar sesión.");
        return "redirect:/login";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "Ocurrió un error inesperado. Por favor, intenta de nuevo más tarde.");
        return "redirect:/register";
    }
}
