package com.okta.aycPedidos.converters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.okta.aycPedidos.entidades.Agenda;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.AgendaModel;

import lombok.RequiredArgsConstructor;

@Component("AgendaConverter")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AgendaConverter extends Converter<AgendaModel, Agenda>{
	
	@Override
	public Agenda modelToEntity(AgendaModel m) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AgendaModel entityToModel(Agenda e) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Agenda> modelsToEntities(List<AgendaModel> m) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AgendaModel> entitiesToModels(List<Agenda> e) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

}
