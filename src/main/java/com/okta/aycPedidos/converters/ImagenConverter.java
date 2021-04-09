package com.okta.aycPedidos.converters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.okta.aycPedidos.entidades.Imagen;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.ImagenModel;

import lombok.RequiredArgsConstructor;

@Component("ImagenConverter")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ImagenConverter extends Converter<ImagenModel, Imagen> {
	
	@Override
	public Imagen modelToEntity(ImagenModel m) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImagenModel entityToModel(Imagen e) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Imagen> modelsToEntities(List<ImagenModel> m) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ImagenModel> entitiesToModels(List<Imagen> e) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

}
