package com.okta.aycPedidos.modelos;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ComentarioModel implements Serializable {

	private static final long serialVersionUID = -3033713906098933782L;

	private String id;
	
	private String contenido;
	
	private UsuarioModel autor;
	
	private PedidoModel pedido;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaAlta;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaBaja;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaModificacion;
	
}
