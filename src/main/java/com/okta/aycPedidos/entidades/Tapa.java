package com.okta.aycPedidos.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Tapa {
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

	
	private String codigoFondo;
	
	private String codigoFrase;
	
	private String customFrase;
	
	@ManyToMany
	private List<Imagen> customImagenes;
	
}
