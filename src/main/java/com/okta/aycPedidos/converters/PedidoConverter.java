package com.okta.aycPedidos.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.okta.aycPedidos.entidades.Agenda;
import com.okta.aycPedidos.entidades.Imagen;
import com.okta.aycPedidos.entidades.Pedido;
import com.okta.aycPedidos.entidades.Usuario;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.PedidoModel;
import com.okta.aycPedidos.repositories.AgendaRepository;
import com.okta.aycPedidos.repositories.ImagenRepository;
import com.okta.aycPedidos.repositories.PedidoRepository;
import com.okta.aycPedidos.repositories.UsuarioRepository;
import com.okta.aycPedidos.services.AgendaService;

import lombok.RequiredArgsConstructor;

@Component("PedidoConverter")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PedidoConverter extends Converter<PedidoModel, Pedido> {

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private AgendaRepository agendaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ImagenRepository imagenRepository;
	@Autowired
	private AgendaConverter agendaConverter;
	@Autowired
	private AgendaService agendaService;
	@Autowired
	private UsuarioConverter usuarioConverter;
	@Autowired
	private ImagenConverter imagenConverter;

	public Pedido modelToEntity(PedidoModel model) throws WebException {

		Pedido entity;

		if (model.getId() != null) {
			entity = pedidoRepository.getOne(model.getId());
		} else {
			entity = new Pedido();
		}

		try {

			Agenda agendaEntity = null;
			if (model.getAgenda().getId() != null && !model.getAgenda().getId().isEmpty()) {
				agendaEntity = agendaRepository.getOne(model.getAgenda().getId());
			} else {
				agendaEntity = agendaService.persistir(model.getAgenda());
			}
			entity.setAgenda(agendaEntity);

			Usuario vendedorEntity = null;
			if (model.getIdVendedor() != null && !model.getIdVendedor().isEmpty()) {
				vendedorEntity = usuarioRepository.getOne(model.getIdVendedor());
			}
			entity.setVendedor(vendedorEntity);

			Usuario disenadorEntity = null;
			if (model.getIdVendedor() != null && !model.getIdVendedor().isEmpty()) {
				disenadorEntity = usuarioRepository.getOne(model.getIdVendedor());
			}
			entity.setDisenador(disenadorEntity);

			Imagen previewEntity = null;
			if (model.getIdPreview() != null && !model.getIdPreview().isEmpty()) {
				previewEntity = imagenRepository.getOne(model.getIdPreview());
			}
			entity.setPreview(previewEntity);

			BeanUtils.copyProperties(model, entity);
		} catch (Exception e) {
			throw new WebException("error al convertir el modelo " + model.toString() + " a entidad");
		}

		return entity;
	}

	public PedidoModel entityToModel(Pedido entity) throws WebException {

		PedidoModel model = new PedidoModel();

		try {

			if (entity.getAgenda() != null) {
				model.setAgenda(agendaConverter.entityToModel(agendaRepository.getOne(entity.getAgenda().getId())));
				model.setIdAgenda(entity.getAgenda().getId());
			}

			if (entity.getVendedor() != null) {
				model.setVendedor(
						usuarioConverter.entityToModel(usuarioRepository.getOne(entity.getVendedor().getId())));
				model.setIdVendedor(entity.getVendedor().getId());
			}

			if (entity.getDisenador() != null) {
				model.setDisenador(
						usuarioConverter.entityToModel(usuarioRepository.getOne(entity.getDisenador().getId())));
				model.setIdDisenador(entity.getDisenador().getId());
			}

			if (entity.getPreview() != null) {
				model.setPreview(imagenConverter.entityToModel(imagenRepository.getOne(entity.getPreview().getId())));
				model.setIdPreview(entity.getPreview().getId());
			}

			BeanUtils.copyProperties(entity, model);

		} catch (Exception e) {
			throw new WebException("Error al convertir la entidad " + entity.toString() + " a modelo");
		}

		return model;
	}

	public List<Pedido> modelsToEntities(List<PedidoModel> models) throws WebException {

		List<Pedido> entities = new ArrayList<>();

		for (PedidoModel model : models) {
			entities.add(modelToEntity(model));
		}

		return entities;
	}

	public List<PedidoModel> entitiesToModels(List<Pedido> entities) throws WebException {

		List<PedidoModel> models = new ArrayList<>();

		for (Pedido entity : entities) {
			models.add(entityToModel(entity));
		}

		return models;
	}

}
