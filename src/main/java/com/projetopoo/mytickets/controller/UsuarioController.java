package com.projetopoo.mytickets.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetopoo.mytickets.model.Usuario;
import com.projetopoo.mytickets.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // POST /usuarios — cria um novo usuário
    // Mande só: { "nome": "Bruno", "email": "bruno@email.com" }
    // NÃO mande o "id" no JSON, o banco gera automaticamente!
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Corrigido: retorna 201 Created em vez de 200
    public Usuario criar(@RequestBody Usuario usuario) {
        return service.criarUsuario(usuario);
    }

    // GET /usuarios — lista todos os usuários
    @GetMapping
    public List<Usuario> listar() {
        return service.listarUsuarios();
    }

    // Corrigido: GET /usuarios/{id} — busca um usuário pelo id
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}
