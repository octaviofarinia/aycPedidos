package com.okta.aycPedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.okta.aycPedidos.entidades.Tapa;

@Repository
public interface TapaRepository extends JpaRepository<Tapa, String> {
	
}
