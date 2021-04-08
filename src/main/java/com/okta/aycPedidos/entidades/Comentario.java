package com.okta.aycPedidos.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	
    //@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
    private Usuario autor;
	
	//@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
    private Pedido pedido;
	
	@Enumerated(EnumType.STRING)
	private TipoComentario tipo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta;
    
    @Temporal(TemporalType.TIMESTAMP)
	private Date fechaBaja;
    
    @Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;
    
}
