package com.okta.aycPedidos.entidades;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.okta.aycPedidos.enums.Estado;

import lombok.Data;

@Data
@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer cantidad;
    
    private String nombreCliente;
    
    
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Agenda agenda;
    
    @ManyToOne
    private Usuario vendedor;
    
    @ManyToOne
    private Usuario disenador;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Imagen preview;
	
    @Temporal(TemporalType.DATE)
	private Date fechaAlta;
    
    @Temporal(TemporalType.DATE)
	private Date fechaBaja;
    
    @Temporal(TemporalType.DATE)
	private Date fechaModificacion;
}
