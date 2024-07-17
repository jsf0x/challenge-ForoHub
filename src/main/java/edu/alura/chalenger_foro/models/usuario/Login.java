package edu.alura.chalenger_foro.models.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Login(
    @NotBlank @NotNull @Email String email,
    @NotBlank @NotNull String contrasena) {

}
