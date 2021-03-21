package com.okta.aycPedidos.repositories;

import com.okta.aycPedidos.entities.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author octav
 */
@Repository
public interface FotoRepository extends JpaRepository<Foto,String>{

}
