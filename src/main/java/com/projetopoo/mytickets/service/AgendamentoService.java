package com.projetopoo.mytickets.service;

import com.projetopoo.mytickets.exception.EntityNotFoundException;
import com.projetopoo.mytickets.model.Agendamento;
import com.projetopoo.mytickets.model.Evento;
import com.projetopoo.mytickets.model.Usuario;
import com.projetopoo.mytickets.model.dtos.AgendamentoDTO;
import com.projetopoo.mytickets.model.dtos.AgendamentoResponseDTO;
import com.projetopoo.mytickets.repository.AgendamentoRepository;
import com.projetopoo.mytickets.repository.EventoRepository;
import com.projetopoo.mytickets.repository.UsuarioRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.projetopoo.mytickets.security.CustomUserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EventoRepository eventoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository,
                              UsuarioRepository usuarioRepository,
                              EventoRepository eventoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.eventoRepository = eventoRepository;
    }

    @Transactional
    public Agendamento criarAgendamento(AgendamentoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + dto.userId()));

        Evento evento = eventoRepository.findById(dto.eventId())
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado com ID: " + dto.eventId()));

        Agendamento agendamento = new Agendamento();
        agendamento.setUser(usuario);
        agendamento.setEvent(evento);
        agendamento.setPersonCount(dto.personCount());

        if (dto.bookedAt() != null) {
            agendamento.setBookedAt(dto.bookedAt());
        }

        return agendamentoRepository.save(agendamento);
    }

    @Transactional(readOnly = true)
    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Agendamento buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado com ID: " + id));
    }

    @Transactional
    public void excluirAgendamento(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado com ID: " + id));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            if (auth != null && auth.getPrincipal() instanceof CustomUserDetails) {
                CustomUserDetails principal = (CustomUserDetails) auth.getPrincipal();
                Long currentUserId = principal.getUsuario().getIdUsuario();
                if (agendamento.getUser() == null || !agendamento.getUser().getIdUsuario().equals(currentUserId)) {
                    throw new AccessDeniedException("Você não tem permissão para excluir este agendamento.");
                }
            } else {
                throw new AccessDeniedException("Acesso negado.");
            }
        }

        agendamentoRepository.delete(agendamento);
    }

    public AgendamentoResponseDTO toResponseDTO(Agendamento a) {
        return new AgendamentoResponseDTO(
                a.getIdAgendamento(),
                a.getBookedAt(),
                a.getUser().getName(),
                a.getEvent().getEventName(),
                a.getPersonCount()
        );
    }
}
