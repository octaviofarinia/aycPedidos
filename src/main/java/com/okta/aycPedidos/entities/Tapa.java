package com.okta.aycPedidos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author octav
 */
@Entity
public class Tapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer codigoFondo;
    private Integer codigoFrase;
    private String nombre;
//    private Int fondoCustom;
//    private Int fraseCustom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoFondo() {
        return codigoFondo;
    }

    public void setCodigoFondo(Integer codigoFondo) {
        this.codigoFondo = codigoFondo;
    }

    public Integer getCodigoFrase() {
        return codigoFrase;
    }

    public void setCodigoFrase(Integer codigoFrase) {
        this.codigoFrase = codigoFrase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
