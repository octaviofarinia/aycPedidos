package com.okta.aycPedidos.entidades;

import javax.persistence.GeneratedValue;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.okta.aycPedidos.enums.Rol;

import lombok.Data;

@Data
@Entity
public class Usuario {
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
	
    private String username;
    
    private String password;
    
    private String mail;
	
    @Enumerated(EnumType.STRING)
    private Rol rol;
	
    @Temporal(TemporalType.DATE)
	private Date fechaAlta;
    
    @Temporal(TemporalType.DATE)
	private Date fechaBaja;
    
    @Temporal(TemporalType.DATE)
	private Date fechaModificacion;
}
