package com.okta.aycPedidos.repositories;

import com.okta.aycPedidos.entities.Tapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author octav
 */
@Repository
public interface TapaRepository extends JpaRepository<Tapa,Long>{

}
