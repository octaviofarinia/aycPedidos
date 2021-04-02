package com.okta.aycPedidos.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.okta.aycPedidos.enums.TipoComentario;

import lombok.Data;

@Data
@Entity
public class Comentario {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

    private String contenido;
	
	@ManyToOne
    private Usuario autor;
	
	@ManyToOne
    private Pedido pedido;
	
	@Enumerated(EnumType.STRING)
	private TipoComentario tipo;
	
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
    
    @Temporal(TemporalType.DATE)
	private Date fechaBaja;
    
    @Temporal(TemporalType.DATE)
	private Date fechaModificacion;
    
}
