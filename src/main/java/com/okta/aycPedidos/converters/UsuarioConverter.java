package com.okta.aycPedidos.converters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.okta.aycPedidos.entidades.Usuario;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.UsuarioModel;

import lombok.RequiredArgsConstructor;

@Component("UsuarioConverter")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UsuarioConverter extends Converter<UsuarioModel, Usuario>{
	
	public Usuario modelToEntity(UsuarioModel m) throws WebException {
		return null;
	}

	public UsuarioModel entityToModel(Usuario e) throws WebException {
		return null;
	}

	public List<Usuario> modelsToEntities(List<UsuarioModel> m) throws WebException {
		return null;
	}

	public List<UsuarioModel> entitiesToModels(List<Usuario> e) throws WebException {
		return null;
	}

}
