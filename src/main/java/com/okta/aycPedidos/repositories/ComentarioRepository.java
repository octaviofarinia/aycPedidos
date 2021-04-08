package com.okta.aycPedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.okta.aycPedidos.entidades.Comentario;
import com.okta.aycPedidos.entidades.Pedido;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, String>{

	@Query("SELECT c FROM Comentario c WHERE c.pedido LIKE :pedido AND c.tipo LIKE 'DESCRIPCION'")
	public Comentario buscarDescripcionPorPedido(@Param("pedido") Pedido pedido);
	
	@Query("SELECT c FROM Comentario c WHERE c.pedido = :pedido AND c.tipo LIKE 'COMENTARIO'")
	public List<Comentario> listarComentariosPorPedido(@Param("pedido") Pedido pedido);
	
	@Query("SELECT c FROM Comentario c WHERE c.pedido LIKE :pedido")
	public List<Comentario> listarTodosLosComentariosPorPedido(@Param("pedido") Pedido pedido);
}
