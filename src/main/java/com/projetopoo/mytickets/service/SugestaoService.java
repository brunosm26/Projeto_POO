package com.projetopoo.mytickets.service;

import com.projetopoo.mytickets.exception.EntityNotFoundException;
import com.projetopoo.mytickets.model.Sugestao;
import com.projetopoo.mytickets.model.Usuario;
import com.projetopoo.mytickets.model.dtos.SugestaoDTO;
import com.projetopoo.mytickets.model.dtos.SugestaoResponseDTO;
import com.projetopoo.mytickets.repository.SugestaoRepository;
import com.projetopoo.mytickets.repository.UsuarioRepository;
import com.projetopoo.mytickets.security.CustomUserDetails;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SugestaoService {

    private final SugestaoRepository sugestaoRepository;
    private final UsuarioRepository usuarioRepository;

    public SugestaoService(SugestaoRepository sugestaoRepository,
                           UsuarioRepository usuarioRepository) {
        this.sugestaoRepository = sugestaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Sugestao criarSugestao(SugestaoDTO dto) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUsuario().getIdUsuario();

        Usuario criador = usuarioRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + userId));

        Sugestao sugestao = new Sugestao();
        sugestao.setEventName(dto.eventName());
        sugestao.setDescription(dto.description());
        sugestao.setCategory(dto.category());
        sugestao.setCreator(criador);

        return sugestaoRepository.save(sugestao);
    }

    @Transactional(readOnly = true)
    public List<Sugestao> listarSugestoes() {
        return sugestaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Sugestao buscarPorId(Long id) {
        return sugestaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sugestão não encontrada com ID: " + id));
    }

    public SugestaoResponseDTO toResponseDTO(Sugestao s) {
        return new SugestaoResponseDTO(
                s.getIdSugestao(),
                s.getEventName(),
                s.getDescription(),
                s.getCategory(),
                s.getStatus(),
                s.getCreator() != null ? s.getCreator().getName() : null
        );
    }
    @Transactional
    public void excluirSugestao(Long id) {
        Sugestao sugestao = sugestaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sugestão não encontrada com ID: " + id));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            if (auth != null && auth.getPrincipal() instanceof CustomUserDetails) {
                CustomUserDetails principal = (CustomUserDetails) auth.getPrincipal();
                Long currentUserId = principal.getUsuario().getIdUsuario();
                if (sugestao.getCreator() == null || !sugestao.getCreator().getIdUsuario().equals(currentUserId)) {
                    throw new AccessDeniedException("Você não tem permissão para excluir esta sugestão.");
                }
            } else {
                throw new AccessDeniedException("Acesso negado.");
            }
        }

        sugestaoRepository.delete(sugestao);
    }
}
