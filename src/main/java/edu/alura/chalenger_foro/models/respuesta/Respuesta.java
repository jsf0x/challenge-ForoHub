package edu.alura.chalenger_foro.models.respuesta;

import java.time.LocalDateTime;

import edu.alura.chalenger_foro.models.topico.Topico;
import edu.alura.chalenger_foro.models.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "respuestas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne
    private Topico topico;
    private LocalDateTime fecha;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    private String solucion;
    private boolean activo;

    public Respuesta(DatosRespuesta respuesta,Topico topico,Usuario autor){
        this.mensaje = respuesta.mensaje();
        this.topico = topico;
        this.fecha = respuesta.fecha();
        this.autor = autor;
        this.solucion = respuesta.solucion();
        this.activo = true;
    }

    public void actualizarRespuesta(DatosActualizarRespuesta respuesta){
        if(respuesta.mensaje()!=null){
            this.mensaje = respuesta.mensaje();
        }
        if(respuesta.solucion()!=null){
            this.solucion = respuesta.solucion();
        }
    }

    public void desativar(){
        this.activo = false;
    }

}
