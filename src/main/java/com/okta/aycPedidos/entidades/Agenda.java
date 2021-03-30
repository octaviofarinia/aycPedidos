package com.okta.aycPedidos.entidades;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.okta.aycPedidos.enums.CodigoInterior;

import lombok.Data;

@Data
@Entity
public class Agenda {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Enumerated(EnumType.STRING)
	private CodigoInterior codigoInterior;

	@OneToOne
	private Tapa tapa;
	@OneToOne
	private Tapa contratapa;
}
