package com.projetopoo.mytickets.service;

import com.projetopoo.mytickets.exception.BusinessException;
import com.projetopoo.mytickets.exception.EntityNotFoundException;
import com.projetopoo.mytickets.model.Evento;
import com.projetopoo.mytickets.model.Inscricao;
import com.projetopoo.mytickets.model.Usuario;
import com.projetopoo.mytickets.model.dtos.InscricaoDTO;
import com.projetopoo.mytickets.repository.EventoRepository;
import com.projetopoo.mytickets.repository.InscricaoRepository;
import com.projetopoo.mytickets.repository.UsuarioRepository;
import com.projetopoo.mytickets.security.CustomUserDetails;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EventoRepository eventoRepository;

    public InscricaoService(InscricaoRepository inscricaoRepository,
                            UsuarioRepository usuarioRepository,
                            EventoRepository eventoRepository) {
        this.inscricaoRepository = inscricaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.eventoRepository = eventoRepository;
    }

    @Transactional
    public Inscricao salvarInscricao(InscricaoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + dto.userId()));

        Evento evento = eventoRepository.findById(dto.eventId())
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado com ID: " + dto.eventId()));

        List<Inscricao> inscricoesExistentes = inscricaoRepository.findByEvent_IdEvento(dto.eventId());
        int ocupado = inscricoesExistentes.stream().mapToInt(Inscricao::getVisitorCount).sum();

        if (ocupado + dto.visitorCount() > evento.getCapacity()) {
            throw new BusinessException("Capacidade insuficiente. Vagas disponíveis: " + (evento.getCapacity() - ocupado));
        }

        Inscricao inscricao = new Inscricao();
        inscricao.setUser(usuario);
        inscricao.setEvent(evento);
        inscricao.setRegistrationAt(dto.registrationAt() != null ? dto.registrationAt() : LocalDateTime.now());
        inscricao.setVisitorCount(dto.visitorCount());

        return inscricaoRepository.save(inscricao);
    }

    @Transactional(readOnly = true)
    public List<Inscricao> listarTodas() {
        return inscricaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Inscricao> listarInscricoesUsuarioLogado() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUsuario().getIdUsuario();
        return inscricaoRepository.findByUser_IdUsuario(userId);
    }

    @Transactional
    public void excluirInscricao(Long id) {
        Inscricao inscricao = inscricaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inscrição não encontrada com ID: " + id));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            if (auth != null && auth.getPrincipal() instanceof CustomUserDetails) {
                CustomUserDetails principal = (CustomUserDetails) auth.getPrincipal();
                Long currentUserId = principal.getUsuario().getIdUsuario();
                if (inscricao.getUser() == null || !inscricao.getUser().getIdUsuario().equals(currentUserId)) {
                    throw new AccessDeniedException("Você não tem permissão para excluir esta inscrição.");
                }
            } else {
                throw new AccessDeniedException("Acesso negado.");
            }
        }

        inscricaoRepository.delete(inscricao);
    }
}
