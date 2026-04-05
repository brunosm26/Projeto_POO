package com.projetopoo.mytickets.config;

import com.projetopoo.mytickets.model.Usuario;
import com.projetopoo.mytickets.model.enums.Role;
import com.projetopoo.mytickets.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class MockDataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public MockDataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            String encodedPassword = passwordEncoder.encode("123456");

            Usuario admin = new Usuario();
            admin.setName("Admin User");
            admin.setEmail("admin@example.com");
            admin.setUsername("admin123");
            admin.setRole(Role.ADMIN);
            admin.setPasswordHash(encodedPassword);

            Usuario normal = new Usuario();
            normal.setName("Normal User");
            normal.setEmail("user@example.com");
            normal.setUsername("user123");
            normal.setRole(Role.USER);
            normal.setPasswordHash(encodedPassword);

            usuarioRepository.save(admin);
            usuarioRepository.save(normal);

            System.out.println("   Admin: admin@example.com | Pass: 123456");
            System.out.println("   User : user@example.com  | Pass: 123456");
        }
    }
}
