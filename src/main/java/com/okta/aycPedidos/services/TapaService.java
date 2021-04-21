package com.okta.aycPedidos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.okta.aycPedidos.converters.TapaConverter;
import com.okta.aycPedidos.entidades.Imagen;
import com.okta.aycPedidos.entidades.Tapa;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.TapaModel;
import com.okta.aycPedidos.repositories.TapaRepository;

@Service
public class TapaService {

	@Autowired
	private TapaRepository tapaRepository;
	@Autowired
	private ImagenService imagenService;
	@Autowired
	private TapaConverter tapaConverter;

	@Transactional
	public Tapa registrarTapa(String codigoFondo, String codigoFrase, MultipartFile[] imagenesMultipartFile,
			String customFrase) throws Exception {

		Tapa tapa = new Tapa();

		try {

			tapa.setCodigoFondo(codigoFondo);

			tapa.setCodigoFrase(codigoFrase);

			if (!customFrase.isEmpty() || customFrase != null) {
				tapa.setCustomFrase(customFrase);
			}

			List<Imagen> imagenesCustom = new ArrayList<Imagen>();
			tapa.setCustomImagenes(imagenesCustom);

			for (MultipartFile imagenMF : imagenesMultipartFile) {
				Imagen imagen = imagenService.guardar(imagenMF);
				imagenesCustom.add(imagen);
			}

			tapa.setCustomImagenes(imagenesCustom);

			tapaRepository.save(tapa);

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

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

	//Registra nuevas tapas o modifica una ya creada
	@Transactional
	public Tapa persistir(TapaModel tapaModel) throws WebException {
		Tapa tapaEntity = tapaConverter.modelToEntity(tapaModel);
		return tapaRepository.save(tapaEntity);
	}

	@Transactional
	public void agregarImagenCustom(String idTapa, MultipartFile imagenMultipartFile) throws Exception {
		Tapa tapa = tapaRepository.getOne(idTapa);

		Imagen imagen = imagenService.guardar(imagenMultipartFile);

		List<Imagen> imagenesCustom = tapa.getCustomImagenes();
		imagenesCustom.add(imagen);

		tapa.setCustomImagenes(imagenesCustom);
		tapaRepository.save(tapa);
	}

}
