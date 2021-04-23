package com.okta.aycPedidos.modelos;

import java.io.Serializable;
import java.util.Base64;

import lombok.Data;

@Data
public class ImagenModel implements Serializable {

	private static final long serialVersionUID = -7444396536768629046L;

	private String id;
	
	private String nombre;
	
	private String mime;
	
	private byte[] contenido;
	
	public String getImgData() {
        return Base64.getMimeEncoder().encodeToString(contenido);
    }
}
