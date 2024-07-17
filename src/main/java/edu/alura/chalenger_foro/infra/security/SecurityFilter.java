package edu.alura.chalenger_foro.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import edu.alura.chalenger_foro.service.ServiceToken;
import edu.alura.chalenger_foro.infra.mis_execpciones.TokenInvalido;
import edu.alura.chalenger_foro.repository.UsuarioRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private ServiceToken tokenService;

    @Autowired
    private UsuarioRepository repository;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Obtener el token del header
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            String email;
            try {
                email = tokenService.getSubject(token);
                if (email != null) {
                    // Token valido
                    var usuario = repository.cojerUsuarioPorEmail(email);
                    if (!usuario.isPresent())
                        throw new IllegalStateException("Usuario no existe");
                    var authentication = new UsernamePasswordAuthenticationToken(usuario.get(), null,
                            usuario.get().getAuthorities()); // Forzamos un inicio de sesion
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (TokenInvalido e) {
                throw new IllegalStateException();
            } // extract username
            

        }
        filterChain.doFilter(request, response);
    }

}
