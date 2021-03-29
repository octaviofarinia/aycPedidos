package com.okta.aycPedidos.entidades;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


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
    private Pedido estado;

    @OneToOne
    private Agenda agenda;
    
    @ManyToOne
    private Usuario vendedor;
    
    @ManyToOne
    private Usuario disenador;
    
    @OneToOne
    private Imagen preview;
	
}
