package com.okta.aycPedidos.repositories;

import com.okta.aycPedidos.entities.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author octav
 */
@Repository
public interface AgendaRepository extends JpaRepository<Agenda,Long>{

}
