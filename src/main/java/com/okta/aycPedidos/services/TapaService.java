package com.okta.aycPedidos.services;

import com.okta.aycPedidos.entities.Foto;
import com.okta.aycPedidos.entities.Tapa;
import com.okta.aycPedidos.repositories.TapaRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author octav
 */
@Service
public class TapaService {

    @Autowired
    private TapaRepository tapaRepository;
    @Autowired
    private FotoService fotoService;

    @Transactional
    public void registrarTapa(Integer codigoFondo, Integer codigoFrase, MultipartFile fotoMF) {
        Tapa tapa = new Tapa();

        tapa.setCodigoFondo(codigoFondo);
        tapa.setCodigoFrase(codigoFrase);
        if (fotoMF != null) {
            Foto foto = fotoService.guardar(fotoMF);
            tapa.getFondosCustom().add(foto);
        }

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
