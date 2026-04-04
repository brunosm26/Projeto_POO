package com.projetopoo.mytickets.controller;

import com.projetopoo.mytickets.model.Usuario;
import com.projetopoo.mytickets.model.dtos.AuthRequest;
import com.projetopoo.mytickets.model.dtos.RegisterRequest;
import com.projetopoo.mytickets.model.dtos.TokenResponse;
import com.projetopoo.mytickets.model.dtos.UsuarioResponse;
import com.projetopoo.mytickets.security.CustomUserDetails;
import com.projetopoo.mytickets.security.TokenService;
import com.projetopoo.mytickets.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager,
                          TokenService tokenService,
                          UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
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
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse register(@Valid @RequestBody RegisterRequest request) {
        // Lógica de registro centralizada no UsuarioService — sem acesso direto ao repositório aqui
        Usuario usuario = usuarioService.registrar(request);
        return new UsuarioResponse(
                usuario.getIdUsuario(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getUpdatedAt()
        );
    }
    @PostMapping("/register-admin")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse registerAdmin(@Valid @RequestBody RegisterRequest request) {
        Usuario usuario = usuarioService.registrarAdmin(request);
        return new UsuarioResponse(
                usuario.getIdUsuario(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getUpdatedAt()
        );
    }
}
