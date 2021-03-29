package com.okta.aycPedidos.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

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
	
}
