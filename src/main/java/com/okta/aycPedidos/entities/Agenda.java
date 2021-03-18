package com.okta.aycPedidos.entities;

import com.okta.aycPedidos.enums.CodigoProducto;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author octav
 */
@Entity
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private CodigoProducto codigo;
    
    @OneToOne
    private Tapa tapa;
    @OneToOne
    private Tapa contratapa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CodigoProducto getCodigo() {
        return codigo;
    }

    public void setCodigo(CodigoProducto codigo) {
        this.codigo = codigo;
    }

    public Tapa getTapa() {
        return tapa;
    }

    public void setTapa(Tapa tapa) {
        this.tapa = tapa;
    }

    public Tapa getContratapa() {
        return contratapa;
    }

    public void setContratapa(Tapa contratapa) {
        this.contratapa = contratapa;
    }
    
    
}
