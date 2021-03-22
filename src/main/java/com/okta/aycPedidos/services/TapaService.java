package com.okta.aycPedidos.services;

import com.okta.aycPedidos.entities.Tapa;
import com.okta.aycPedidos.repositories.TapaRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author octav
 */
@Service
public class TapaService {

    @Autowired
    private TapaRepository tapaRepository;

    @Transactional
    public void registrarTapa(Integer codigoFondo, Integer codigoFrase, String nombre) {
        Tapa tapa = new Tapa();

        tapa.setCodigoFondo(codigoFondo);
        tapa.setCodigoFrase(codigoFrase);

        tapaRepository.save(tapa);
    }

    @Transactional
    public void modificarTapa(Long idTapa, Integer codigoFondo, Integer codigoFrase, String nombre) {
        Tapa modificada = tapaRepository.getOne(idTapa);

        modificada.setCodigoFondo(codigoFondo);
        modificada.setCodigoFrase(codigoFrase);

        tapaRepository.save(modificada);
    }

    @Transactional
    public void eliminarTapa(Long idTapa) {
        tapaRepository.deleteById(idTapa);
    }

}
