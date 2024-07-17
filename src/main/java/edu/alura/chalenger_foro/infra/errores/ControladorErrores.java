package edu.alura.chalenger_foro.infra.errores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.alura.chalenger_foro.infra.mis_execpciones.*;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControladorErrores {

    @ExceptionHandler({EntityNotFoundException.class,NoExiste.class})
    public ResponseEntity<String> tratarError404(){
        return ResponseEntity.notFound().build();
    }
    
    @ExceptionHandler({MethodArgumentNotValidException.class,DatosInvalidos.class})
    public ResponseEntity<List<ErrorDTO>> tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(ErrorDTO::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }
    @ExceptionHandler({TokenInvalido.class,ProblemasGenerarToken.class,IllegalStateException.class})
    public ResponseEntity<String> tratarErrorTokenInvalido(TokenInvalido e){
        return ResponseEntity.status(401).body("Token inválido");
    }
    private record ErrorDTO(String campo, String error){
        public ErrorDTO(FieldError error){
            this(error.getField(),error.getDefaultMessage());
        }
    }

}
