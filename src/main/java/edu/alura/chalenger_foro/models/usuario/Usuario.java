package edu.alura.chalenger_foro.models.usuario;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(unique = true)
    private String email;
    private String contrasena;
    @Enumerated(EnumType.STRING)
    private Perfil perfil;
    private boolean activo;

    public Usuario(DatosUsuario usuario) {
        this.nombre = usuario.nombre();
        this.email = usuario.email();
        this.perfil = usuario.perfil();
        this.contrasena = usuario.contrasena();
        this.activo = true;
    }

    public void actualizarUsuario(DatosActualizarUsuario usuario) {
        if (usuario.nombre() != null) {
            this.nombre = usuario.nombre();
        }
        if (usuario.contrasena() != null) {
            this.contrasena = usuario.contrasena();
        }
    }

    public void desativar() {
        this.activo = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        switch (this.perfil) {
            case ADMINISTRADOR:
                return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
            case MODERADOR:
                return List.of(new SimpleGrantedAuthority("ROLE_MODERATOR"));
            case ESTUDIANTE:
                return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
            case INSTRUCTOR:
                return List.of(new SimpleGrantedAuthority("ROLE_INSTRUCTOR"));
            default:
                throw new IllegalArgumentException("Perfil desconocido: " + this.perfil);
        }

    }

    @Override
    public String getPassword() {
        return this.contrasena;
    }

    @Override
    public String getUsername() {
        return this.nombre;
    }

}
