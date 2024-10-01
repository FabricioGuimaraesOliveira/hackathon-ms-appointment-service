package com.fiap.hackathon.appointmentservice.infrastructure.authentication;

import com.fiap.hackathon.appointmentservice.usecases.agendamento.AuthorizationUseCase;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthorizationUseCase authorizationUseCase;

    public JwtAuthenticationFilter(AuthorizationUseCase authorizationUseCase) {
        this.authorizationUseCase = authorizationUseCase;
    }



    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        String path = request.getRequestURI();
        if (path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui") || path.startsWith("/swagger-resources") || path.startsWith("/webjars")) {
            filterChain.doFilter(request, response);
            return;
        }
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token ausente ou mal formatado.");
            return;
        }

        String token = authorizationHeader.replace("Bearer ", "");

        if (!authorizationUseCase.validarToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token inválido.");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
