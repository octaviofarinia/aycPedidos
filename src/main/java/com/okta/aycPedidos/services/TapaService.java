package com.okta.aycPedidos.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.okta.aycPedidos.entidades.Imagen;
import com.okta.aycPedidos.entidades.Tapa;
import com.okta.aycPedidos.repositories.TapaRepository;

@Service
public class TapaService {

	@Autowired
	private TapaRepository tapaRepository;
	@Autowired
	private ImagenService imagenService;

	// De momento solo registra una imagen en customFondos
	@Transactional
	public Tapa registrarTapa(String codigoFondo, String codigoFrase, MultipartFile imagenMultipartFile,
			String customFrase) throws Exception {
		Tapa tapa = new Tapa();

		tapa.setCodigoFondo(codigoFondo);

		tapa.setCodigoFrase(codigoFrase);

		if (!customFrase.isEmpty() || customFrase != null) {
			tapa.setCustomFrase(customFrase);
		}

		this.agregarImagenCustom(tapa.getId(), imagenMultipartFile);

		tapaRepository.save(tapa);

		return tapa;
	}

	// No modifica las imagenes
	@Transactional
	public void modificarTapa(String idTapa, String codigoFondo, String codigoFrase, String customFrase) {

		Optional<Tapa> respuesta = tapaRepository.findById(idTapa);

		if (respuesta.isPresent()) {
			Tapa modificada = tapaRepository.getOne(idTapa);

			if (!codigoFondo.isEmpty() || codigoFondo != null) {
				modificada.setCodigoFondo(codigoFondo);
			}
			if (!codigoFrase.isEmpty() || codigoFrase != null) {
				modificada.setCodigoFrase(codigoFrase);
			}
			if (!customFrase.isEmpty() || customFrase != null) {
				modificada.setCustomFrase(customFrase);
			}

			tapaRepository.save(modificada);
		}
	}

	@Transactional
	public void eliminarTapa(String idTapa) {
		tapaRepository.deleteById(idTapa);
	}

	@Transactional
	public void agregarImagenCustom(String idTapa, MultipartFile imagenMultipartFile) throws Exception {
		Tapa tapa = new Tapa();

		Imagen imagen = imagenService.guardar(imagenMultipartFile);

		List<Imagen> imagenesCustom = tapa.getCustomImagenes();
		imagenesCustom.add(imagen);

		tapa.setCustomImagenes(imagenesCustom);
	}

}
