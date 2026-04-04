package com.projetopoo.mytickets.service;

import com.projetopoo.mytickets.exception.BusinessException;
import com.projetopoo.mytickets.exception.EntityNotFoundException;
import com.projetopoo.mytickets.model.Usuario;
import com.projetopoo.mytickets.model.dtos.RegisterRequest;
import com.projetopoo.mytickets.model.enums.Role;
import com.projetopoo.mytickets.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario registrar(RegisterRequest request) {
        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            throw new BusinessException("Já existe um usuário com o e-mail: " + request.email());
        }

        Usuario usuario = new Usuario();
        usuario.setName(request.name());
        usuario.setEmail(request.email());
        usuario.setUsername(request.username());
        usuario.setPasswordHash(passwordEncoder.encode(request.password()));
        // Role.USER é setado pelo @PrePersist na entidade

        return usuarioRepository.save(usuario);
    }

    // Usado apenas pelo endpoint /api/auth/register-admin (profile dev)
    @Transactional
    public Usuario registrarAdmin(RegisterRequest request) {
        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            throw new BusinessException("Já existe um usuário com o e-mail: " + request.email());
        }

        Usuario usuario = new Usuario();
        usuario.setName(request.name());
        usuario.setEmail(request.email());
        usuario.setUsername(request.username());
        usuario.setPasswordHash(passwordEncoder.encode(request.password()));
        usuario.setRole(Role.ADMIN); // força ADMIN antes do @PrePersist rodar

        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
    }

    @Transactional
    public Usuario atualizar(Long id, RegisterRequest request) {
        Usuario usuario = buscarPorId(id);

        // Checa duplicata de email apenas se o email mudou
        if (!usuario.getEmail().equals(request.email())) {
            if (usuarioRepository.findByEmail(request.email()).isPresent()) {
                throw new BusinessException("Já existe um usuário com o e-mail: " + request.email());
            }
        }

        usuario.setName(request.name());
        usuario.setEmail(request.email());
        usuario.setUsername(request.username());

        if (request.password() != null && !request.password().isBlank()) {
            usuario.setPasswordHash(passwordEncoder.encode(request.password()));
        }

        return usuarioRepository.save(usuario);
    }
}