package com.okta.aycPedidos.converters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.okta.aycPedidos.entidades.Pedido;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.PedidoModel;

import lombok.RequiredArgsConstructor;

@Component("PedidoConverter")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PedidoConverter extends Converter<PedidoModel, Pedido>{
	
	@Override
	public Pedido modelToEntity(PedidoModel m) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PedidoModel entityToModel(Pedido e) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> modelsToEntities(List<PedidoModel> m) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PedidoModel> entitiesToModels(List<Pedido> e) throws WebException {
		// TODO Auto-generated method stub
		return null;
	}

}
