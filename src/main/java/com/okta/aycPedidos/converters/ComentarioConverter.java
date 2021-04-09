package com.okta.aycPedidos.converters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.okta.aycPedidos.entidades.Comentario;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.ComentarioModel;

import lombok.RequiredArgsConstructor;

@Component("ComentarioConverter")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ComentarioConverter extends Converter<ComentarioModel, Comentario>{
	
	@Override
	public Comentario modelToEntity(ComentarioModel m) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComentarioModel entityToModel(Comentario e) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comentario> modelsToEntities(List<ComentarioModel> m) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComentarioModel> entitiesToModels(List<Comentario> e) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

}
