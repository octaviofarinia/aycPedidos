package com.okta.aycPedidos.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okta.aycPedidos.converters.AgendaConverter;
import com.okta.aycPedidos.entidades.Agenda;
import com.okta.aycPedidos.entidades.Tapa;
import com.okta.aycPedidos.enums.CodigoInterior;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.AgendaModel;
import com.okta.aycPedidos.repositories.AgendaRepository;

@Service
public class AgendaService {

	@Autowired
	private AgendaRepository agendaRepository;
	@Autowired
	private AgendaConverter agendaConverter;
	
	@Transactional
	public Agenda registrarAgenda(String codigoInterior, Tapa tapa, Tapa contratapa) {

		Agenda agenda = new Agenda();

		try {

			agenda.setCodigoInterior(CodigoInterior.valueOf(codigoInterior));

			agenda.setTapa(tapa);

			agenda.setContratapa(contratapa);

			agendaRepository.save(agenda);

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		return agenda;
	}

	@Transactional
	public void modificarAgenda(String agendaId, String codigoInterior, Tapa tapa, Tapa contratapa) {

		Optional<Agenda> respuesta = agendaRepository.findById(agendaId);

		if (respuesta.isPresent()) {
			Agenda agendaModificada = respuesta.get();

			agendaModificada.setCodigoInterior(CodigoInterior.valueOf(codigoInterior));

			agendaModificada.setTapa(tapa);

			agendaModificada.setContratapa(contratapa);

			agendaRepository.save(agendaModificada);
		}

	}

	// Registra nuevas agendas o modifica una ya creada
	@Transactional
	public Agenda persistir(AgendaModel agendaModel) throws WebException {
		Agenda agendaEntity = agendaConverter.modelToEntity(agendaModel);
		return agendaRepository.save(agendaEntity);
	}

}
