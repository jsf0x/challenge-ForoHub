package edu.alura.chalenger_foro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.alura.chalenger_foro.DTO.DatosDTOTopico;
import edu.alura.chalenger_foro.infra.mis_execpciones.NoExiste;
import edu.alura.chalenger_foro.models.topico.DatosActualizarTopico;
import edu.alura.chalenger_foro.models.topico.DatosTopico;
import edu.alura.chalenger_foro.models.topico.Topico;
import edu.alura.chalenger_foro.repository.CursoRepository;
import edu.alura.chalenger_foro.repository.TopicoRepository;
import edu.alura.chalenger_foro.repository.UsuarioRepository;
import jakarta.validation.Valid;

@Service
public class ServiceTopico {
    @Autowired
    private TopicoRepository repository;
    @Autowired
    private UsuarioRepository userR;
    @Autowired
    private CursoRepository cursoR;

    public Page<DatosDTOTopico> getAllTopicos(Pageable page) {
        var datos = repository.findByStatusTrue(page);
        return datos.map(d -> new DatosDTOTopico(d));
    }

    public Topico registrarTopico(@Valid DatosTopico datoTopico) throws NoExiste {
        var user = userR.findById(datoTopico.autor());
        if(!user.isPresent()){
            throw new NoExiste("No existe esse usuario");
        }
        var curso = cursoR.findById(datoTopico.curso());
        if(!curso.isPresent()){
            throw new NoExiste("No existe esse curso");
        }
        var topico = new Topico(datoTopico,user.get(),curso.get());
        repository.save(topico);
        return topico;
    }

    public DatosDTOTopico getTopicoById(Long id) throws NoExiste {
        var topico = existe(id);
        return new DatosDTOTopico(topico);
    }

    public DatosDTOTopico updateTopico(Long id, DatosActualizarTopico datosTopico) throws NoExiste {
        var topico = existe(id);
        topico.actualizarTopico(datosTopico);
        return new DatosDTOTopico(topico);
    }

    public void deletarTopico(Long id) throws NoExiste {
       var topico = existe(id);
       topico.desativar();
    }

    private Topico existe(Long id) throws NoExiste{
        var topico = repository.findByIdAndStatusTrue(id);
        if(!topico.isPresent())
            throw new NoExiste("No existe esse topico");
        return topico.get();
    }
}
