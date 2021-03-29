package com.okta.aycPedidos.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Comentario {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

    private String contenido;
	
	@OneToOne
    private Usuario autor;
	
	@OneToOne
    private Pedido pedido;
	
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
    
    @Temporal(TemporalType.DATE)
	private Date fechaBaja;
    
    @Temporal(TemporalType.DATE)
	private Date fechaModificacion;
    
}
