package com.okta.aycPedidos.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.okta.aycPedidos.entidades.Imagen;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.ImagenModel;
import com.okta.aycPedidos.repositories.ImagenRepository;

import lombok.RequiredArgsConstructor;

@Component("ImagenConverter")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ImagenConverter extends Converter<ImagenModel, Imagen> {
	
	@Autowired
	private ImagenRepository imagenRepository;
	
	public Imagen modelToEntity(ImagenModel model) throws WebException {
		
		Imagen entity;
		
		try {
			
			if(model.getId() != null && !model.getId().isEmpty()) {
				entity = imagenRepository.getOne(model.getId());
			}else {
				entity = new Imagen();
			}

			BeanUtils.copyProperties(model, entity);
			
		} catch (Exception e) {
			throw new WebException("error al convertir el modelo " + model.toString() + " a entidad");
		}
		
		return entity;
	}

	public ImagenModel entityToModel(Imagen entity) throws WebException {
		
		ImagenModel model = new ImagenModel();
		
		try {
			BeanUtils.copyProperties(entity, model);
		} catch (Exception e) {
			throw new WebException("Error al convertir la entidad " + entity.toString() + " a modelo");
		}
		
		return model;
	}

	public List<Imagen> modelsToEntities(List<ImagenModel> models) throws WebException {
		
		List<Imagen> entities = new ArrayList<>();
		
		for (ImagenModel model : models) {
			entities.add(modelToEntity(model));
		}
		
		return entities;
	}

	public List<ImagenModel> entitiesToModels(List<Imagen> entities) throws WebException {
		
		List<ImagenModel> models = new ArrayList<>();
		
		for (Imagen entity : entities) {
			models.add(entityToModel(entity));
		}
		
		return models;
	}

}
