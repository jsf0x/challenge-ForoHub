package edu.alura.chalenger_foro.models.respuesta;

import java.time.LocalDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRespuesta(
        @NotNull @NotBlank String mensaje,
        @NotNull @Valid long topico,
        @NotNull @FutureOrPresent LocalDateTime fecha,
        @NotNull @Valid long autor,
        @NotBlank @NotNull String solucion) {
                
}
