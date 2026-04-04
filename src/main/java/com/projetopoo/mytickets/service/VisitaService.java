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
        return visitaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Visita buscarPorId(Long id) {
        return visitaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Visita não encontrada com ID: " + id));
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
