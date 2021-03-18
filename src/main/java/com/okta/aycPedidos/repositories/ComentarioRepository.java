package com.okta.aycPedidos.repositories;

import com.okta.aycPedidos.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author octav
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Long>{

}
