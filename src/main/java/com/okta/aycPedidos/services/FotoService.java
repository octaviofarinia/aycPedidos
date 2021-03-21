package com.okta.aycPedidos.services;

import com.okta.aycPedidos.entities.Foto;
import com.okta.aycPedidos.repositories.FotoRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author octav
 */
@Service
public class FotoService {

	@Autowired
	private FotoRepository fotoRepository;
	
	
	
	@Transactional
	public Foto guardar(MultipartFile archivo){
		
		if(archivo != null) {
			try {
				
				
				
				Foto foto = new Foto();
				
				foto.setMime(archivo.getContentType());
				foto.setNombre(archivo.getName());
				foto.setContenido(archivo.getBytes());
				
				return fotoRepository.save(foto);
				
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		return null;
	}
}
