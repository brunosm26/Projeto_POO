package com.projetopoo.mytickets.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    public Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails principal) {
            return principal.getUsuario().getIdUsuario();
        }
        throw new AccessDeniedException("Acesso negado.");
    }

    public void verifyOwnership(Long ownerId, String errorMsg) {
        if (isAdmin()) return;
        Long currentUserId = getCurrentUserId();
        if (ownerId == null || !ownerId.equals(currentUserId)) {
            throw new AccessDeniedException(errorMsg);
        }
    }
}
