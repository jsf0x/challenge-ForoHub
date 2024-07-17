package edu.alura.chalenger_foro.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @SuppressWarnings({ "deprecation", "removal" })
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Le indiamos a Spring el tipo de sesion
                .and().authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/login","/login/**").permitAll()
                .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()

                .requestMatchers(HttpMethod.PUT, "/curso", "/respuesta", "/topico", "/curso/**", "/respuesta/**",
                        "/topico/**").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(HttpMethod.DELETE, "/curso", "/respuesta", "/topico", "/curso/**", "/respuesta/**",
                        "/topico/**").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(HttpMethod.POST, "/curso", "/topico", "/curso/**", "/topico/**")
                .hasAnyAuthority("ROLE_INSTRUCTOR", "ROLE_ADMIN")

                .requestMatchers(HttpMethod.DELETE, "/respuesta", "/respuesta/**")
                .hasAnyAuthority("ROLE_INSTRUCTOR","ROLE_MODERATOR")

                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
