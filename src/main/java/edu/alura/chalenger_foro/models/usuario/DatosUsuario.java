package edu.alura.chalenger_foro.models.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DatosUsuario(
        @NotBlank @NotNull String nombre,
        @NotBlank @NotNull @Email String email,
        @NotNull Perfil perfil,
        @NotBlank @NotNull @Size(min = 5) String contrasena) {

}
