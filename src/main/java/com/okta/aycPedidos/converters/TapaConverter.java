package com.okta.aycPedidos.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.okta.aycPedidos.entidades.Imagen;
import com.okta.aycPedidos.entidades.Tapa;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.ImagenModel;
import com.okta.aycPedidos.modelos.TapaModel;
import com.okta.aycPedidos.repositories.TapaRepository;

import lombok.RequiredArgsConstructor;

@Component("TapaConverter")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TapaConverter extends Converter<TapaModel, Tapa> {

	@Autowired
	private TapaRepository tapaRepository;

	@Autowired
	private ImagenConverter imagenConverter;

	public Tapa modelToEntity(TapaModel model) throws WebException {

		Tapa entity;

		if (model.getId() != null && !model.getId().isEmpty()) {
			entity = tapaRepository.getOne(model.getId());
		} else {
			entity = new Tapa();
		}

		try {

			List<Imagen> imagenesE = imagenConverter.modelsToEntities(model.getCustomImagenes());
			entity.setCustomImagenes(imagenesE);

			BeanUtils.copyProperties(model, entity);
		} catch (Exception e) {
			throw new WebException("error al convertir el modelo " + model.toString() + " a entidad");
		}

		return entity;
	}

	public TapaModel entityToModel(Tapa entity) throws WebException {

		TapaModel model = new TapaModel();

		try {

			List<ImagenModel> imaganesM = imagenConverter.entitiesToModels(entity.getCustomImagenes());
			model.setCustomImagenes(imaganesM);

			List<String> idImagenesM = new ArrayList<>();
			for (ImagenModel imagenM : imaganesM) {
				idImagenesM.add(imagenM.getId());
			}

			model.setIdsCustomImagenes(idImagenesM);

			BeanUtils.copyProperties(entity, model);

		} catch (Exception e) {
			throw new WebException("Error al convertir la entidad " + entity.toString() + " a modelo");
		}

		return model;
	}

	public List<Tapa> modelsToEntities(List<TapaModel> models) throws WebException {

		List<Tapa> entities = new ArrayList<>();

		for (TapaModel model : models) {
			entities.add(modelToEntity(model));
		}

		return entities;
	}

	public List<TapaModel> entitiesToModels(List<Tapa> entities) throws WebException {

		List<TapaModel> models = new ArrayList<>();

		for (Tapa entity : entities) {
			models.add(entityToModel(entity));
		}

		return models;
	}

}
