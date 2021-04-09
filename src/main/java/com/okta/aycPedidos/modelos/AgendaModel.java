package com.okta.aycPedidos.modelos;

import java.io.Serializable;

import com.okta.aycPedidos.enums.CodigoInterior;

import lombok.Data;

@Data
public class AgendaModel implements Serializable {

	private static final long serialVersionUID = 3072070623148623744L;

	private String id;
	
	private CodigoInterior codigoInterior;
	
	private TapaModel tapa;
	
	private TapaModel contratapa;
	
}
