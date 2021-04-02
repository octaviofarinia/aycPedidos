package com.okta.aycPedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.okta.aycPedidos.entidades.Agenda;
import com.okta.aycPedidos.entidades.Pedido;
import com.okta.aycPedidos.entidades.Tapa;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, String>{
	
	@Query("SELECT a.tapa FROM Agenda a WHERE a.id LIKE :id")
    public Tapa retornarTapa(@Param("id") String agendaId);
	
	@Query("SELECT a.contratapa FROM Agenda a WHERE a.id LIKE :id")
    public Tapa retornarContratapa(@Param("id") String agendaId);
	
}
