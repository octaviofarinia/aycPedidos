package com.okta.aycPedidos.entidades;

import java.util.Base64;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Imagen {

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nombre;
    
    private String mime;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;
    
    public String getImgData() {
        return Base64.getMimeEncoder().encodeToString(contenido);
    }
    
}
