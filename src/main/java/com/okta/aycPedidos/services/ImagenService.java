package com.okta.aycPedidos.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.okta.aycPedidos.entidades.Imagen;
import com.okta.aycPedidos.repositories.ImagenRepository;

@Service
public class ImagenService {

	@Autowired
	private ImagenRepository imagenRepository;

	@Transactional
	public Imagen guardar(MultipartFile archivo) throws Exception {

		if (archivo != null) {
			try {

				Imagen imagen = new Imagen();

				imagen.setMime(archivo.getContentType());
				imagen.setNombre(archivo.getName());
				imagen.setContenido(archivo.getBytes());

				return imagenRepository.save(imagen);

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		return null;
	}

}
