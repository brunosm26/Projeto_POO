package com.projetopoo.mytickets.service;

import com.projetopoo.mytickets.exception.EntityNotFoundException;
import com.projetopoo.mytickets.model.Evento;
import com.projetopoo.mytickets.model.TipoEvento;
import com.projetopoo.mytickets.model.Usuario;
import com.projetopoo.mytickets.model.dtos.EventoDTO;
import com.projetopoo.mytickets.model.enums.EventCategory;
import com.projetopoo.mytickets.repository.EventoRepository;
import com.projetopoo.mytickets.repository.TipoEventoRepository;
import com.projetopoo.mytickets.repository.UsuarioRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TipoEventoRepository tipoEventoRepository;

    public EventoService(EventoRepository eventoRepository,
            UsuarioRepository usuarioRepository,
            TipoEventoRepository tipoEventoRepository) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
        this.tipoEventoRepository = tipoEventoRepository;
    }

    @Transactional
    public Evento criarEvento(EventoDTO dto) {
        Evento evento = new Evento();
        evento.setEventName(dto.eventName());
        evento.setDescription(dto.description());
        evento.setCapacity(dto.capacity());
        evento.setPrice(dto.price());
        evento.setExpectedAttendance(dto.expectedAttendance());
        evento.setFree(dto.isFree());
        evento.setWeekend(dto.isWeekend());
        evento.setScheduledAt(dto.scheduledAt());
        evento.setCategory(dto.category());
        evento.setImageUrl(dto.imageUrl());
        evento.setLocationDetail(dto.locationDetail());

        if (dto.creatorId() != null) {
            Usuario criador = usuarioRepository.findById(dto.creatorId())
                    .orElseThrow(
                            () -> new EntityNotFoundException("Criador não encontrado com ID: " + dto.creatorId()));
            evento.setCreator(criador);
        }

        if (dto.eventTypeId() != null) {
            TipoEvento tipo = tipoEventoRepository.findById(dto.eventTypeId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Tipo de evento não encontrado com ID: " + dto.eventTypeId()));
            evento.setEventType(tipo);
        }

        return eventoRepository.save(evento);
    }

    @Transactional(readOnly = true)
    public List<Evento> listarEventos(EventCategory category) {
        if (category != null) {
            return eventoRepository.findByCategory(category);
        }
        return eventoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Evento buscarPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado com ID: " + id));
    }

    @Transactional
    public Evento adicionarAdmin(Long eventoId, Long usuarioId) {
        Evento evento = buscarPorId(eventoId);
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + usuarioId));

        boolean jaEAdmin = evento.getAdmins() != null &&
                evento.getAdmins().stream().anyMatch(a -> a.getIdUsuario().equals(usuarioId));

        if (jaEAdmin) {
            throw new com.projetopoo.mytickets.exception.BusinessException("Usuário já é admin deste evento");
        }

        if (evento.getAdmins() == null) {
            evento.setAdmins(new java.util.ArrayList<>());
        }
        evento.getAdmins().add(usuario);
        return eventoRepository.save(evento);
    }

    @Transactional
    public Evento removerAdmin(Long eventoId, Long usuarioId) {
        Evento evento = buscarPorId(eventoId);

        if (evento.getAdmins() == null) {
            throw new com.projetopoo.mytickets.exception.BusinessException("Este evento não tem admins");
        }

        evento.getAdmins().removeIf(a -> a.getIdUsuario().equals(usuarioId));
        return eventoRepository.save(evento);
    }

    @Transactional
    public Evento atualizarEvento(Long id, EventoDTO dto) {
        Evento evento = buscarPorId(id);
        evento.setEventName(dto.eventName());
        evento.setDescription(dto.description());
        evento.setCapacity(dto.capacity());
        evento.setPrice(dto.price());
        evento.setExpectedAttendance(dto.expectedAttendance());
        evento.setFree(dto.isFree());
        evento.setWeekend(dto.isWeekend());
        evento.setScheduledAt(dto.scheduledAt());
        evento.setCategory(dto.category());
        evento.setImageUrl(dto.imageUrl());
        evento.setLocationDetail(dto.locationDetail());
        return eventoRepository.save(evento);
    }

    @Transactional
    public void deletarEvento(Long id) {
        Evento evento = buscarPorId(id);
        eventoRepository.delete(evento);
    }

    public EventoDTO toDTO(Evento e) {
        return new EventoDTO(
                e.getIdEvento(),
                e.getEventName(),
                e.getDescription(),
                e.getCapacity(),
                e.getLocationDetail(),
                e.getCreator() != null ? e.getCreator().getIdUsuario() : null,
                e.getFree(),
                e.getScheduledAt(),
                e.getEventType() != null ? e.getEventType().getIdTipoEvento() : null,
                e.getPrice(),
                e.getExpectedAttendance(),
                e.getWeekend(),
                e.getCategory(),
                e.getCreatedAt(),
                e.getUpdatedAt(),
                e.getImageUrl());
    }
}
