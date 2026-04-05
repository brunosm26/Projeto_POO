package com.projetopoo.mytickets.service;

import com.projetopoo.mytickets.exception.EntityNotFoundException;
import com.projetopoo.mytickets.model.Visita;
import com.projetopoo.mytickets.model.Usuario;
import com.projetopoo.mytickets.model.dtos.VisitaDTO;
import com.projetopoo.mytickets.model.dtos.VisitaResponseDTO;
import com.projetopoo.mytickets.repository.UsuarioRepository;
import com.projetopoo.mytickets.repository.VisitaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.AccessDeniedException;
import com.projetopoo.mytickets.security.CustomUserDetails;

@Service
public class VisitaService {

    private final VisitaRepository visitaRepository;
    private final UsuarioRepository usuarioRepository;

    public VisitaService(VisitaRepository visitaRepository,
                         UsuarioRepository usuarioRepository) {
        this.visitaRepository = visitaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Visita criarVisita(VisitaDTO dto) {
        Usuario solicitante = usuarioRepository.findById(dto.requesterId())
                .orElseThrow(() -> new EntityNotFoundException("Solicitante não encontrado com ID: " + dto.requesterId()));

        Visita visita = new Visita();
        visita.setScheduledAt(dto.scheduledAt());
        visita.setRequester(solicitante);
        visita.setIsAuthorized(false);

        if (dto.authorizerId() != null) {
            Usuario autorizador = usuarioRepository.findById(dto.authorizerId())
                    .orElseThrow(() -> new EntityNotFoundException("Autorizador não encontrado com ID: " + dto.authorizerId()));
            visita.setAuthorizer(autorizador);
        }

        return visitaRepository.save(visita);
    }

    @Transactional(readOnly = true)
    public List<Visita> listarVisitas() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        List<Visita> todasVisitas = visitaRepository.findAll();

        if (isAdmin) {
            return todasVisitas;
        }

        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails principal = (CustomUserDetails) auth.getPrincipal();
            Long currentUserId = principal.getUsuario().getIdUsuario();
            return todasVisitas.stream()
                    .filter(v -> v.getRequester() != null && v.getRequester().getIdUsuario().equals(currentUserId))
                    .toList();
        }

        throw new AccessDeniedException("Acesso negado.");
    }

    @Transactional(readOnly = true)
    public Visita buscarPorId(Long id) {
        Visita visita = visitaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Visita não encontrada com ID: " + id));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            if (auth != null && auth.getPrincipal() instanceof CustomUserDetails) {
                CustomUserDetails principal = (CustomUserDetails) auth.getPrincipal();
                Long currentUserId = principal.getUsuario().getIdUsuario();
                if (visita.getRequester() == null || !visita.getRequester().getIdUsuario().equals(currentUserId)) {
                    throw new AccessDeniedException("Você não tem permissão para visualizar esta visita.");
                }
            } else {
                 throw new AccessDeniedException("Acesso negado.");
            }
        }

        return visita;
    }

    public VisitaResponseDTO toResponseDTO(Visita v) {
        return new VisitaResponseDTO(
                v.getIdVisita(),
                v.getScheduledAt(),
                v.getIsAuthorized(),
                v.getRequester() != null ? v.getRequester().getName() : null,
                v.getAuthorizer() != null ? v.getAuthorizer().getName() : null
        );
    }
}
