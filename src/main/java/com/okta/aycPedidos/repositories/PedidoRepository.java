package com.okta.aycPedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.okta.aycPedidos.entidades.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
