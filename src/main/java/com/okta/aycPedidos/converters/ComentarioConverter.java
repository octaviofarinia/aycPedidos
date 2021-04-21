package com.okta.aycPedidos.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.okta.aycPedidos.entidades.Comentario;
import com.okta.aycPedidos.entidades.Pedido;
import com.okta.aycPedidos.entidades.Usuario;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.ComentarioModel;
import com.okta.aycPedidos.repositories.ComentarioRepository;
import com.okta.aycPedidos.repositories.PedidoRepository;
import com.okta.aycPedidos.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Component("ComentarioConverter")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ComentarioConverter extends Converter<ComentarioModel, Comentario> {

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PedidoConverter pedidoConverter;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioConverter usuarioConverter;

	public Comentario modelToEntity(ComentarioModel model) throws WebException {

		Comentario entity;

		if (model.getId() != null && !model.getId().isEmpty()) {
			entity = comentarioRepository.getOne(model.getId());
		} else {
			entity = new Comentario();
		}

		try {

			Usuario autorEntity = null;
			if (model.getIdAutor() != null && !model.getIdAutor().isEmpty()) {
				autorEntity = usuarioRepository.getOne(model.getIdAutor());
			}
			entity.setAutor(autorEntity);

			Pedido pedidoEntity = null;
			if (model.getIdPedido() != null) {
				pedidoEntity = pedidoRepository.getOne(model.getIdPedido());
			}
			entity.setPedido(pedidoEntity);

			BeanUtils.copyProperties(model, entity);

		} catch (Exception e) {
			throw new WebException("Error al convertir la entidad " + entity.toString() + " a modelo");
		}

		return entity;
	}

	public ComentarioModel entityToModel(Comentario entity) throws WebException {

		ComentarioModel model = new ComentarioModel();

		try {

			if (entity.getAutor() != null) {
				model.setAutor(usuarioConverter.entityToModel(usuarioRepository.getOne(entity.getAutor().getId())));
				model.setIdAutor(entity.getAutor().getId());
			}

			if (entity.getPedido() != null) {
				model.setPedido(pedidoConverter.entityToModel(pedidoRepository.getOne(entity.getPedido().getId())));
				model.setIdPedido(entity.getPedido().getId());
			}

			BeanUtils.copyProperties(entity, model);

		} catch (Exception e) {
			throw new WebException("Error al convertir la entidad " + entity.toString() + " a modelo");
		}

		return model;
	}

	public List<Comentario> modelsToEntities(List<ComentarioModel> models) throws WebException {
		
		List<Comentario> entities = new ArrayList<>();

		for (ComentarioModel model : models) {
			entities.add(modelToEntity(model));
		}

		return entities;
	}

	public List<ComentarioModel> entitiesToModels(List<Comentario> entities) throws WebException {
		
		List<ComentarioModel> models = new ArrayList<>();

		for (Comentario entity : entities) {
			models.add(entityToModel(entity));
		}

		return models;
	}

}
