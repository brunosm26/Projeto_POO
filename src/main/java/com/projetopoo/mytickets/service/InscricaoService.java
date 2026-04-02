package com.projetopoo.mytickets.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projetopoo.mytickets.model.Evento;
import com.projetopoo.mytickets.model.Inscricao;
import com.projetopoo.mytickets.model.Usuario;
import com.projetopoo.mytickets.repository.EventoRepository;
import com.projetopoo.mytickets.repository.InscricaoRepository;
import com.projetopoo.mytickets.repository.UsuarioRepository;

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

    public Inscricao inscrever(Long usuarioId, Long eventoId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        List<Inscricao> inscricoes = inscricaoRepository.findByEventoId(eventoId);

        int occupied = inscricoes.stream().mapToInt(Inscricao::getQuantidadeVisitantes).sum();

        if (occupied >= evento.getCapacidade()) {
            throw new RuntimeException("Evento lotado");
        }

        Inscricao inscricao = new Inscricao();
        inscricao.setUsuario(usuario);
        inscricao.setEvento(evento);
        inscricao.setDataHoraInscricao(LocalDateTime.now());

        return inscricaoRepository.save(inscricao);
    }

    public List<Inscricao> listarPorUsuario(Long usuarioId) {
        return inscricaoRepository.findByUsuarioId(usuarioId);
    }
}
