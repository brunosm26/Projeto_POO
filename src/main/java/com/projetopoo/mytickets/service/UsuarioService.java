package com.projetopoo.mytickets.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projetopoo.mytickets.model.Usuario;
import com.projetopoo.mytickets.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario criarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }
}
