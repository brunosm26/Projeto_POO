package com.projetopoo.mytickets.service;

import com.projetopoo.mytickets.exception.BusinessException;
import com.projetopoo.mytickets.exception.EntityNotFoundException;
import com.projetopoo.mytickets.model.Usuario;
import com.projetopoo.mytickets.model.dtos.RegisterRequest;
import com.projetopoo.mytickets.model.enums.Role;
import com.projetopoo.mytickets.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void registrar_deveSalvarUsuario_quandoEmailNaoExiste() {
        RegisterRequest request = new RegisterRequest("João Silva", "joao@email.com", "senha123", "joaosilva");
        Usuario usuarioSalvo = new Usuario();
        usuarioSalvo.setName(request.name());
        usuarioSalvo.setEmail(request.email());

        when(usuarioRepository.findByEmail(request.email())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.password())).thenReturn("hashSenha");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        Usuario resultado = usuarioService.registrar(request);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getEmail()).isEqualTo("joao@email.com");
    }

    @Test
    void registrar_deveLancarExcecao_quandoEmailJaExiste() {
        RegisterRequest request = new RegisterRequest("João Silva", "joao@email.com", "senha123", "joaosilva");
        Usuario existente = new Usuario();

        when(usuarioRepository.findByEmail(request.email())).thenReturn(Optional.of(existente));

        assertThatThrownBy(() -> usuarioService.registrar(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("joao@email.com");
    }

    @Test
    void registrarAdmin_deveSalvarUsuarioComRoleAdmin() {
        RegisterRequest request = new RegisterRequest("Admin User", "admin@email.com", "senha123", "adminuser");
        Usuario usuarioSalvo = new Usuario();
        usuarioSalvo.setName(request.name());
        usuarioSalvo.setRole(Role.ADMIN);

        when(usuarioRepository.findByEmail(request.email())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.password())).thenReturn("hashSenha");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        Usuario resultado = usuarioService.registrarAdmin(request);

        assertThat(resultado.getRole()).isEqualTo(Role.ADMIN);
    }

    @Test
    void buscarPorId_deveLancarExcecao_quandoUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> usuarioService.buscarPorId(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void atualizar_deveLancarExcecao_quandoNovoEmailJaExiste() {
        RegisterRequest request = new RegisterRequest("João", "novo@email.com", "senha123", "joao");
        Usuario existente = new Usuario();
        existente.setEmail("antigo@email.com");
        Usuario outroUsuario = new Usuario();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(usuarioRepository.findByEmail("novo@email.com")).thenReturn(Optional.of(outroUsuario));

        assertThatThrownBy(() -> usuarioService.atualizar(1L, request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("novo@email.com");
    }
}
