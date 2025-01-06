package org.cexpositoce.coapp.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.cexpositoce.coapp.user.application.service.FindUserByUsernameService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAspect {

    private final FindUserByUsernameService findUserByUsernameService;

    public AuthorizationAspect(FindUserByUsernameService findUserByUsernameService) {
        this.findUserByUsernameService = findUserByUsernameService;
    }

    @Around("@annotation(org.cexpositoce.coapp.common.aop.interfaces.IsAdmin)")
    public Object validateAdminRole(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();

        boolean isAdmin = principal.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ADMIN"));

        if (!isAdmin) {
            throw new PermissionDeniedException("Acceso denegado: No tienes permisos de administrador");
        }

        return joinPoint.proceed();
    }

    @Around("@annotation(org.cexpositoce.coapp.common.aop.interfaces.IsSelfOrAdmin) && args(userId,..)")
    public Object validateSelfOrAdmin(ProceedingJoinPoint joinPoint, Long userId) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();

        boolean isAdmin = principal.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ADMIN"));

        boolean isSelf = findUserByUsernameService.getUserByUsername(principal.getUsername()).getId().equals(userId);

        if (!isAdmin && !isSelf) {
            throw new PermissionDeniedException("Acceso denegado: No tienes permisos para esta operación");

        }

        return joinPoint.proceed();
    }
}
