package com.okta.aycPedidos.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okta.aycPedidos.entidades.Comentario;
import com.okta.aycPedidos.entidades.Pedido;
import com.okta.aycPedidos.entidades.Usuario;
import com.okta.aycPedidos.enums.TipoComentario;
import com.okta.aycPedidos.repositories.ComentarioRepository;

@Service
public class ComentarioService {

	@Autowired
	ComentarioRepository comentarioRepository;
	
	@Transactional
	public void registrarComentario(Pedido pedido, Usuario autor, String contenido, TipoComentario tipo) {
		Comentario comentario = new Comentario();
		
		comentario.setContenido(contenido);
		
		comentario.setAutor(autor);
		
		comentario.setPedido(pedido);
		
		comentario.setTipo(tipo);
		
		comentario.setFechaAlta(new Date());
		
		comentario.setFechaBaja(null);
		
		comentario.setFechaModificacion(null);
		
		comentarioRepository.save(comentario);
	}
	
	@Transactional
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
	
	@Transactional
	public void hardDeleteComentario(String comentarioId) {
		comentarioRepository.deleteById(comentarioId);
	}
	
	@Transactional
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

	@Transactional
	public Comentario buscarDescripcionPorPedido(Pedido pedido) {
		return comentarioRepository.buscarDescripcionPorPedido(pedido);
	}
	
	@Transactional
	public List<Comentario> listarComentariosPorPedido(Pedido pedido) {
		return comentarioRepository.listarComentariosPorPedido(pedido);
	}
	
	@Transactional
	public List<Comentario> listarTodosLosComentariosPorPedido(Pedido pedido) {
		return comentarioRepository.listarTodosLosComentariosPorPedido(pedido);
	}
}
