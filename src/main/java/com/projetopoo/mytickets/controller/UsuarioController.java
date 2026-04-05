package com.projetopoo.mytickets.controller;

import com.projetopoo.mytickets.model.Usuario;
import com.projetopoo.mytickets.model.dtos.RegisterRequest;
import com.projetopoo.mytickets.model.dtos.UsuarioResponse;
import com.projetopoo.mytickets.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse criar(@Valid @RequestBody RegisterRequest request) {
        Usuario usuario = service.registrar(request);
        return toResponse(usuario);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UsuarioResponse> listar() {
        List<Usuario> usuarios = service.listarUsuarios();
        return usuarios.stream().map(this::toResponse).toList();
    }

    @GetMapping("/{idUsuario}")
    @PreAuthorize("hasRole('ADMIN')")
    public UsuarioResponse buscarPorId(@PathVariable Long idUsuario) {
        return toResponse(service.buscarPorId(idUsuario));
    }

    @PutMapping("/{idUsuario}")
    @PreAuthorize("hasRole('ADMIN') or #idUsuario == authentication.principal.usuario.idUsuario")
    public UsuarioResponse atualizar(@PathVariable Long idUsuario,
                                     @Valid @RequestBody RegisterRequest request) {
        return toResponse(service.atualizar(idUsuario, request));
    }

    private UsuarioResponse toResponse(Usuario u) {
        return new UsuarioResponse(
                u.getIdUsuario(),
                u.getName(),
                u.getEmail(),
                u.getUsername(),
                u.getUpdatedAt()
        );
    }
}
