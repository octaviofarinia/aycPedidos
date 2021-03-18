package com.okta.aycPedidos.services;

import com.okta.aycPedidos.entities.Comentario;
import com.okta.aycPedidos.entities.Usuario;
import com.okta.aycPedidos.repositories.ComentarioRepository;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author octav
 */
@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;
    
    @Transactional
    public Comentario crearComentario(String contenido, Usuario creator) {

        Comentario comentario = new Comentario();
        
        comentario.setContenido(contenido);
        comentario.setCreator(creator);
        comentario.setFecha(new Date());
        
        comentarioRepository.save(comentario);

        return comentario;
    }
    
}
