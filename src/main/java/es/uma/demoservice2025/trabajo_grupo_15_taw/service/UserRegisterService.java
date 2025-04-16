package es.uma.demoservice2025.trabajo_grupo_15_taw.service;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {

    @Autowired
    private UsuarioRepository repo;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public void register(String email, String password) {
        try {
            User newUser = new User();
            newUser.setPasswordHash(encoder.encode(password));
            newUser.setEmail(email);
            repo.save(newUser);
            System.out.println("Usuario registrado con éxito: " + email);
        } catch (Exception e) {
            System.out.println("ERROR al registrar usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
