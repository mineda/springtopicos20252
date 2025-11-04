package br.gov.sp.fatec.springtopicos20252.security;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtAuthenticationFilter extends GenericFilterBean {

    private JwtUtils jwt;

    public JwtAuthenticationFilter(JwtUtils jwt) {
        this.jwt = jwt;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
  
        try {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            String authorization = servletRequest.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorization != null) {
                Authentication credentials = 
                    jwt.parseToken(authorization.replaceAll("Bearer ", ""));
                SecurityContextHolder.getContext().setAuthentication(credentials);
            }
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // Se ocorrer qualquer erro na interpretação do token, segue adiante
            // Por não estar autenticado, irá gerar erro se tentar acessar algo protegido
            chain.doFilter(request, response);
        }
    }
  
}

