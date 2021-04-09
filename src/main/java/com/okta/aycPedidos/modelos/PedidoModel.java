package com.okta.aycPedidos.modelos;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.okta.aycPedidos.enums.Estado;

import lombok.Data;

@Data
public class PedidoModel implements Serializable {

	private static final long serialVersionUID = 5556277326812187660L;

	private String id;
	
	private Integer cantidad;
	
	private String nombreCliente;
	
	private Estado estado;
	
	private AgendaModel agenda;
	
	private UsuarioModel vendedor;
	
	private UsuarioModel disenador;
	
	private ImagenModel preview;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaAlta;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaBaja;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaModificacion;
	
}
