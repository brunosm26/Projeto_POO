package com.projetopoo.mytickets.controller;

import com.projetopoo.mytickets.model.DTOs.AuthRequest;
import com.projetopoo.mytickets.model.DTOs.RegisterRequest;
import com.projetopoo.mytickets.model.DTOs.TokenResponse;
import com.projetopoo.mytickets.model.DTOs.UsuarioResponse;
import com.projetopoo.mytickets.model.Usuario;
import com.projetopoo.mytickets.model.enums.Role;
import com.projetopoo.mytickets.repository.UsuarioRepository;
import com.projetopoo.mytickets.security.CustomUserDetails;
import com.projetopoo.mytickets.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService,
                          UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody AuthRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var userDetails = (CustomUserDetails) auth.getPrincipal();
        var token = tokenService.generateToken(userDetails.getUsuario());

        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> register(@RequestBody RegisterRequest request) {
        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(request.nome());
        novoUsuario.setEmail(request.email());
        novoUsuario.setUsername(request.username());
        novoUsuario.setRole(Role.USER);
        novoUsuario.setPasswordHash(passwordEncoder.encode(request.password()));

        Usuario savedUser = usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok(new UsuarioResponse(
                savedUser.getId(),
                savedUser.getNome(),
                savedUser.getEmail(),
                savedUser.getUsername()
        ));
    }
}
