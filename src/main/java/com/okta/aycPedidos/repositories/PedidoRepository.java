package com.okta.aycPedidos.repositories;

import com.okta.aycPedidos.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author octav
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long>{

}
