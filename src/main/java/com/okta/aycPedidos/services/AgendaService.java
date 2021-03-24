package com.okta.aycPedidos.services;

import com.okta.aycPedidos.entities.Agenda;
import com.okta.aycPedidos.entities.Tapa;
import com.okta.aycPedidos.enums.CodigoProducto;
import com.okta.aycPedidos.repositories.AgendaRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author octav
 */
@Service
public class AgendaService {

    @Autowired
    private TapaService tapaService;

    @Autowired
    private AgendaRepository agendaRepository;

    @Transactional
    public void registrarAgenda(String codigoInterior, Tapa tapa, Tapa contratapa) {
        Agenda agenda = new Agenda();

        agenda.setCodigo(CodigoProducto.valueOf(codigoInterior));
        agenda.setTapa(tapa);
        agenda.setContratapa(contratapa);

        agendaRepository.save(agenda);
    }

    @Transactional
    public void modificarAgenda(Long idAgenda, String codigo, Tapa tapa, Tapa contratapa) {
        Agenda agendaModificada = agendaRepository.getOne(idAgenda);

        agendaModificada.setCodigo(CodigoProducto.valueOf(codigo));
        agendaModificada.setTapa(tapa);
        agendaModificada.setContratapa(contratapa);

        agendaRepository.save(agendaModificada);
    }

    @Transactional
    public void eliminarAgenda(Long idAgenda) {
        agendaRepository.deleteById(idAgenda);
    }
}
