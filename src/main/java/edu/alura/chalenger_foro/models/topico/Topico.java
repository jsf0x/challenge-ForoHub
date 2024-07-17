package edu.alura.chalenger_foro.models.topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.alura.chalenger_foro.models.curso.Curso;
import edu.alura.chalenger_foro.models.respuesta.Respuesta;
import edu.alura.chalenger_foro.models.usuario.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name = "topico",uniqueConstraints = {@UniqueConstraint(columnNames = { "titulo","mensaje" })})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    private Boolean status; // Activo o Inactivo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Respuesta> respuestas;

    public Topico(DatosTopico topico,Usuario user,Curso curso){
        this.titulo = topico.titulo();
        this.mensaje = topico.mensaje();
        this.fecha = topico.fecha();
        this.autor = user;
        this.curso = curso;
        this.status = true;
        this.respuestas = new ArrayList<>();
    }

    public Topico(DatosTopico topico,Usuario user,Curso curso,ArrayList<Respuesta> resp){
        this.titulo = topico.titulo();
        this.mensaje = topico.mensaje();
        this.fecha = topico.fecha();
        this.autor = user;
        this.curso = curso;
        this.status = true;
        this.respuestas = resp;
        for (Respuesta respuesta : this.respuestas) {
            respuesta.setTopico(this);
        }
    }

    public void actualizarTopico(DatosActualizarTopico topico){
        if(topico.curso()!=null){
            this.curso.ActualizarCurso(topico.curso());
        }
        if(topico.mensaje()!=null){
            this.mensaje = topico.mensaje();
        }
        if(topico.titulo()!=null){
            this.titulo = topico.titulo();
        }
    }

    public void desativar(){
        this.status = false;
    }

}
