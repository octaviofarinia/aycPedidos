package com.okta.aycPedidos.modelos;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class TapaModel implements Serializable {

	private static final long serialVersionUID = 4102969324074518496L;

	private String id;
	
	private String codigoFondo;
	
	private String codigoFrase;
	
	private String customFrase;
	
	private List<ImagenModel> customImagenes;
	private List<String> idsCustomImagenes;
	
}
