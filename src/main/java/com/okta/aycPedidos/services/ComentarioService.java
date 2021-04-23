package com.okta.aycPedidos.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okta.aycPedidos.converters.ComentarioConverter;
import com.okta.aycPedidos.converters.PedidoConverter;
import com.okta.aycPedidos.entidades.Comentario;
import com.okta.aycPedidos.entidades.Pedido;
import com.okta.aycPedidos.entidades.Usuario;
import com.okta.aycPedidos.enums.TipoComentario;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.ComentarioModel;
import com.okta.aycPedidos.modelos.PedidoModel;
import com.okta.aycPedidos.repositories.ComentarioRepository;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;
	@Autowired
	private ComentarioConverter comentarioConverter;
	@Autowired
	private PedidoConverter pedidoConverter;

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

		if (respuesta.isPresent()) {
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
			throw new Exception("No se encontro el comentario");
		}
	}

	@Transactional
	public Comentario persistir(ComentarioModel comentarioModel) throws WebException {

		Comentario comentarioEntity = comentarioConverter.modelToEntity(comentarioModel);

		if (comentarioEntity.getFechaAlta() != null) {
			comentarioEntity.setFechaModificacion(new Date());
		} else {
			comentarioEntity.setFechaAlta(new Date());
		}

		return comentarioRepository.save(comentarioEntity);
	}

	@Transactional
	public Comentario buscarDescripcionPorPedido(Pedido pedido) {
		return comentarioRepository.buscarDescripcionPorPedido(pedido);
	}
	
	@Transactional
	public ComentarioModel buscarDescripcionPorPedidoMODEL(PedidoModel pedido) throws WebException {
		return comentarioConverter.entityToModel(comentarioRepository.buscarDescripcionPorPedido(pedidoConverter.modelToEntity(pedido)));
	}

	@Transactional
	public List<Comentario> listarComentariosPorPedido(Pedido pedido) {
		return comentarioRepository.listarComentariosPorPedido(pedido);
	}
	
	@Transactional
	public List<ComentarioModel> listarComentariosPorPedidoMODELS(PedidoModel pedidoModel) throws WebException {
		Pedido pedido = pedidoConverter.modelToEntity(pedidoModel);
		return comentarioConverter.entitiesToModels(comentarioRepository.listarComentariosPorPedido(pedido));
	}

	// el nombre del metodo es horrible pero este devuelve todos los comentario incluida la descripcion del pedido xd
	@Transactional
	public List<Comentario> listarTodosLosComentariosPorPedido(Pedido pedido) {
		return comentarioRepository.listarTodosLosComentariosPorPedido(pedido);
	}
}
