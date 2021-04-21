package com.okta.aycPedidos.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.okta.aycPedidos.entidades.Agenda;
import com.okta.aycPedidos.entidades.Tapa;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.AgendaModel;
import com.okta.aycPedidos.repositories.AgendaRepository;
import com.okta.aycPedidos.repositories.TapaRepository;

import lombok.RequiredArgsConstructor;

@Component("AgendaConverter")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AgendaConverter extends Converter<AgendaModel, Agenda>{
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private TapaRepository tapaRepository;
	
	@Autowired
	private TapaConverter tapaConverter;
	
	public Agenda modelToEntity(AgendaModel model) throws WebException {
		
		Agenda entity;
		
		if(model.getId() != null && !model.getId().isEmpty()) {
			entity = agendaRepository.getOne(model.getId());
		} else {
			entity = new Agenda();
		}
		
		try {
			
			Tapa entityTapa = null;
			if(model.getIdTapa() != null && !model.getIdTapa().isEmpty()) {
				entityTapa = tapaRepository.getOne(model.getIdTapa());
			}
			entity.setTapa(entityTapa);
			
			Tapa entityContratapa = null;
			if(model.getIdContratapa() != null && !model.getIdContratapa().isEmpty()) {
				entityContratapa = tapaRepository.getOne(model.getIdContratapa());
			}
			entity.setContratapa(entityContratapa);
			
			BeanUtils.copyProperties(model, entity);
			
		} catch (Exception e) {
			throw new WebException("error al convertir el modelo " + model.toString() + " a entidad");
		}
		
		return entity;
	}

	public AgendaModel entityToModel(Agenda entity) throws WebException {
		
		AgendaModel model = new AgendaModel();
		
		try {
			
			if (entity.getTapa() != null) {
				model.setIdTapa(entity.getTapa().getId());
				model.setTapa(tapaConverter.entityToModel(tapaRepository.getOne(entity.getTapa().getId())));
			}
			
			if (entity.getContratapa() != null) {
				model.setIdContratapa(entity.getContratapa().getId());
				model.setContratapa(tapaConverter.entityToModel(tapaRepository.getOne(entity.getContratapa().getId())));
			}
			
			BeanUtils.copyProperties(entity, model);
			
		} catch (Exception e) {
			throw new WebException("Error al convertir la entidad " + entity.toString() + " a modelo");
		}
		
		return model;
	}

	public List<Agenda> modelsToEntities(List<AgendaModel> models) throws WebException {
		
		List<Agenda> entities = new ArrayList<>();

		for (AgendaModel model : models) {
			entities.add(modelToEntity(model));
		}

		return entities;
	}

	public List<AgendaModel> entitiesToModels(List<Agenda> entities) throws WebException {
		
		List<AgendaModel> models = new ArrayList<>();

		for (Agenda entity : entities) {
			models.add(entityToModel(entity));
		}

		return models;
	}

}
