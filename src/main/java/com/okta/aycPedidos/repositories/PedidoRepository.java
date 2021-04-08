package com.okta.aycPedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.okta.aycPedidos.entidades.Agenda;
import com.okta.aycPedidos.entidades.Pedido;
import com.okta.aycPedidos.entidades.Usuario;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	@Query("SELECT p FROM Pedido p WHERE p.fechaBaja IS NULL")
    public List<Pedido> listarPedidosActivos();
	
	@Query("SELECT p.agenda FROM Pedido p WHERE p.id LIKE :id")
    public Agenda retornarAgenda(@Param("id") Long pedidoId);
	
	@Query("SELECT p FROM Pedido p WHERE p.vendedor = :usu OR p.disenador = :usu")
    public List<Pedido> listarPedidosPorUsuario(@Param("usu") Usuario usuario);
	
}
