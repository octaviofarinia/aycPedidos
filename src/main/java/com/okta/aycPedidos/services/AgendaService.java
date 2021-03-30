package com.okta.aycPedidos.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okta.aycPedidos.entidades.Agenda;
import com.okta.aycPedidos.entidades.Tapa;
import com.okta.aycPedidos.entidades.Usuario;
import com.okta.aycPedidos.enums.CodigoInterior;
import com.okta.aycPedidos.repositories.AgendaRepository;

@Service
public class AgendaService {
	
//	@Autowired
//  private TapaService tapaService;

    @Autowired
    private AgendaRepository agendaRepository;
	
    @Transactional
    public Agenda registrarAgenda(String codigoInterior, Tapa tapa, Tapa contratapa) {
        Agenda agenda = new Agenda();

        agenda.setCodigoInterior(CodigoInterior.valueOf(codigoInterior));
        
        agenda.setTapa(tapa);
        
        agenda.setContratapa(contratapa);

        agendaRepository.save(agenda);
        
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

    @Transactional
    public void eliminarAgenda(String idAgenda) {
        agendaRepository.deleteById(idAgenda);
    }
    
}
