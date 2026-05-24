package com.projetopoo.mytickets.service;

import com.projetopoo.mytickets.exception.BusinessException;
import com.projetopoo.mytickets.exception.EntityNotFoundException;
import com.projetopoo.mytickets.model.Evento;
import com.projetopoo.mytickets.model.Inscricao;
import com.projetopoo.mytickets.model.Usuario;
import com.projetopoo.mytickets.model.dtos.CriarInscricaoDTO;
import com.projetopoo.mytickets.repository.EventoRepository;
import com.projetopoo.mytickets.repository.InscricaoRepository;
import com.projetopoo.mytickets.repository.UsuarioRepository;
import com.projetopoo.mytickets.security.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InscricaoServiceTest {

    @Mock
    private InscricaoRepository inscricaoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private SecurityUtils securityUtils;

    @InjectMocks
    private InscricaoService inscricaoService;

    @Test
    void criar_deveLancarExcecao_quandoUsuarioNaoEncontrado() {
        CriarInscricaoDTO dto = new CriarInscricaoDTO(1L, null);

        when(usuarioRepository.findByEmail("inexistente@email.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> inscricaoService.criar(dto, "inexistente@email.com"))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("inexistente@email.com");
    }

    @Test
    void criar_deveLancarExcecao_quandoEventoNaoEncontrado() {
        CriarInscricaoDTO dto = new CriarInscricaoDTO(999L, null);
        Usuario usuario = new Usuario();

        when(usuarioRepository.findByEmail("user@email.com")).thenReturn(Optional.of(usuario));
        when(eventoRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> inscricaoService.criar(dto, "user@email.com"))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("999");
    }

    @Test
    void criar_deveLancarExcecao_quandoUsuarioJaInscrito() {
        CriarInscricaoDTO dto = new CriarInscricaoDTO(1L, null);
        Usuario usuario = new Usuario();
        Evento evento = new Evento();
        evento.setCapacity(100);

        when(usuarioRepository.findByEmail("user@email.com")).thenReturn(Optional.of(usuario));
        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));
        when(inscricaoRepository.existsByUserAndEvent(usuario, evento)).thenReturn(true);

        assertThatThrownBy(() -> inscricaoService.criar(dto, "user@email.com"))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("já inscrito");
    }

    @Test
    void criar_deveLancarExcecao_quandoCapacidadeInsuficiente() {
        CriarInscricaoDTO dto = new CriarInscricaoDTO(1L, null);
        Usuario usuario = new Usuario();
        Evento evento = new Evento();
        evento.setCapacity(1);

        Inscricao inscricaoExistente = new Inscricao();
        inscricaoExistente.setVisitorCount(1);

        when(usuarioRepository.findByEmail("user@email.com")).thenReturn(Optional.of(usuario));
        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));
        when(inscricaoRepository.existsByUserAndEvent(usuario, evento)).thenReturn(false);
        when(inscricaoRepository.findByEvent_IdEvento(1L)).thenReturn(Collections.singletonList(inscricaoExistente));

        assertThatThrownBy(() -> inscricaoService.criar(dto, "user@email.com"))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Capacidade insuficiente");
    }
}
