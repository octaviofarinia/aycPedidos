package com.okta.aycPedidos.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
    @OneToMany
    private List<Foto> fondosCustom;
    private String fraseCustom;

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

    public List<Foto> getFondosCustom() {
        return fondosCustom;
    }

    public void setFondosCustom(List<Foto> fondosCustom) {
        this.fondosCustom = fondosCustom;
    }

    public String getFraseCustom() {
        return fraseCustom;
    }

    public void setFraseCustom(String fraseCustom) {
        this.fraseCustom = fraseCustom;
    }
    
}
