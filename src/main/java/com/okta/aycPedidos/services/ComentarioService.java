package com.okta.aycPedidos.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okta.aycPedidos.entidades.Comentario;
import com.okta.aycPedidos.entidades.Pedido;
import com.okta.aycPedidos.entidades.Usuario;
import com.okta.aycPedidos.repositories.ComentarioRepository;

@Service
public class ComentarioService {

	@Autowired
	ComentarioRepository comentarioRepository;
	
	public void registrarComentario(Pedido pedido, Usuario autor, String contenido) {
		Comentario comentario = new Comentario();
		
		comentario.setContenido(contenido);
		
		comentario.setAutor(autor);
		
		comentario.setPedido(pedido);
		
		comentario.setFechaAlta(new Date());
		
		comentario.setFechaBaja(null);
		
		comentario.setFechaModificacion(null);
		
		comentarioRepository.save(comentario);
	}
	
	public void modificarComentario(String comentarioId, Pedido pedido, Usuario autor, String contenido) {
		
		Optional<Comentario> respuesta = comentarioRepository.findById(comentarioId);
		
		if(respuesta.isPresent()) {
			Comentario comentarioModificado = respuesta.get();
			
			comentarioModificado.setContenido(contenido);
			
			comentarioModificado.setAutor(autor);
			
			comentarioModificado.setPedido(pedido);
			
			comentarioModificado.setFechaModificacion(new Date());
			
			comentarioRepository.save(comentarioModificado);
		}
	}
	
	public void hardDeleteComentario(String comentarioId) {
		comentarioRepository.deleteById(comentarioId);
	}
	
	public void softDeleteComentario(String comentarioId) throws Exception {
		Optional<Comentario> respuesta = comentarioRepository.findById(comentarioId);

        if (respuesta.isPresent()) {

            Comentario comentario = respuesta.get();

            comentario.setFechaBaja(new Date());
            
            comentarioRepository.save(comentario);
            
        } else {
            throw new Exception("No se encontro el pedido");
        }
	}
	
}
