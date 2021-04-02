package com.okta.aycPedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.okta.aycPedidos.entidades.Agenda;
import com.okta.aycPedidos.entidades.Tapa;

@Repository
public interface TapaRepository extends JpaRepository<Tapa, String> {
	
}
