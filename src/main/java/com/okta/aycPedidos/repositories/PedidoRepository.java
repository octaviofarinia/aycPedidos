package com.okta.aycPedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.okta.aycPedidos.entidades.Agenda;
import com.okta.aycPedidos.entidades.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	@Query("SELECT p FROM Pedido p WHERE p.fechaBaja IS NULL")
    public List<Pedido> listarPedidosActivos();
	
	@Query("SELECT p.agenda FROM Pedido p WHERE p.id LIKE :id")
    public Agenda retornarAgenda(@Param("id") Long pedidoId);
	
}
