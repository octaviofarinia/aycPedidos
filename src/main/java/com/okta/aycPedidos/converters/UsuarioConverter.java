package com.okta.aycPedidos.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.okta.aycPedidos.entidades.Usuario;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.UsuarioModel;
import com.okta.aycPedidos.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Component("UsuarioConverter")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UsuarioConverter extends Converter<UsuarioModel, Usuario>{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario modelToEntity(UsuarioModel model) throws WebException {
		
		Usuario entity;
		
		if (model.getId() != null && !model.getId().isEmpty()) {
			entity = usuarioRepository.getOne(model.getId());
		} else {
			entity = new Usuario();
		}
		
		try {
			
			BeanUtils.copyProperties(model, entity);
			
		} catch (Exception e) {
			throw new WebException("error al convertir el modelo " + model.toString() + " a entidad");
		}
		
		return entity;
	}

	public UsuarioModel entityToModel(Usuario entity) throws WebException {
		
		UsuarioModel model = new UsuarioModel();
		
		try {
			
			BeanUtils.copyProperties(entity, model);
			
		} catch (Exception e) {
			throw new WebException("Error al convertir la entidad " + entity.toString() + " a modelo");
		}
		
		return model;
	}

	public List<Usuario> modelsToEntities(List<UsuarioModel> models) throws WebException {
		
		List<Usuario> entities = new ArrayList<>();

		for (UsuarioModel model : models) {
			entities.add(modelToEntity(model));
		}

		return entities;
	}

	public List<UsuarioModel> entitiesToModels(List<Usuario> entities) throws WebException {
		
		List<UsuarioModel> models = new ArrayList<>();

		for (Usuario entity : entities) {
			models.add(entityToModel(entity));
		}

		return models;
	}

}
