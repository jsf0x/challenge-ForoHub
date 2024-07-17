package edu.alura.chalenger_foro.models.topico;

import java.time.LocalDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DatosTopico(

        @NotNull @NotBlank @Size(max = 50,min = 5) String titulo,
        @NotNull @NotBlank @Size(max = 255,min = 5) String mensaje,
        @NotNull @FutureOrPresent LocalDateTime fecha,
        @NotNull @Valid long autor,
        @NotNull @Valid long curso) {
                

}
