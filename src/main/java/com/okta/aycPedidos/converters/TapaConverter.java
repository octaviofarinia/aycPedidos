package com.okta.aycPedidos.converters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.okta.aycPedidos.entidades.Tapa;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.TapaModel;

import lombok.RequiredArgsConstructor;

@Component("TapaConverter")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TapaConverter extends Converter<TapaModel, Tapa>{
	
	@Override
	public Tapa modelToEntity(TapaModel m) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TapaModel entityToModel(Tapa e) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tapa> modelsToEntities(List<TapaModel> m) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TapaModel> entitiesToModels(List<Tapa> e) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

}
