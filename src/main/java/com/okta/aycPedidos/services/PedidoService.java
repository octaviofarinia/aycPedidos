package com.okta.aycPedidos.services;

import com.okta.aycPedidos.entities.Agenda;
import com.okta.aycPedidos.entities.Comentario;
import com.okta.aycPedidos.entities.Pedido;
import com.okta.aycPedidos.entities.Usuario;
import com.okta.aycPedidos.enums.Estado;
import com.okta.aycPedidos.repositories.PedidoRepository;
import java.util.ArrayList;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author octav
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ComentarioService comentarioService;

    @Transactional
    public void validarPedido(Integer cantidad, Date fechaEmision, String estado, Agenda agenda, Usuario vendedor, ArrayList<Comentario> comentarios) throws Exception {
        if (cantidad <= 0) {
            throw new Exception("la cantidad debe ser mayor o igual a 1");
        }
    }

    @Transactional
    public void registrarPedido(Integer cantidad, Date fechaEmision, String descripcion, String estado, Agenda agenda, Usuario vendedor, ArrayList<Comentario> comentarios) throws Exception {

        validarPedido(cantidad, fechaEmision, estado, agenda, vendedor, comentarios);

        Pedido pedido = new Pedido();

        pedido.setCantidad(cantidad);
        pedido.setFechaEmision(fechaEmision);
        pedido.setEstado(Estado.valueOf(estado));
        pedido.setAgenda(agenda);
        pedido.setVendedor(vendedor);
        pedido.setComentarios(comentarios);

        pedidoRepository.save(pedido);

    }

    @Transactional
    public void modificarPedido(Long pedidoId, Integer cantidad, Date fechaEmision, String descripcion, String estado, Agenda agenda, Usuario vendedor) {

        Pedido pedidoModificado = pedidoRepository.getOne(pedidoId);

        pedidoModificado.setCantidad(cantidad);
        pedidoModificado.setFechaEmision(fechaEmision);
        pedidoModificado.setEstado(Estado.valueOf(estado));
        pedidoModificado.setAgenda(agenda);
        pedidoModificado.setVendedor(vendedor);

        pedidoRepository.save(pedidoModificado);
    }

    @Transactional
    public void eliminarPedido(Long pedidoId) {

        pedidoRepository.deleteById(pedidoId);

    }

    @Transactional
    public void agregarComentario(Long pedidoId, String contenido, Usuario creator) {

       Comentario comentario = comentarioService.crearComentario(contenido, creator);
       Pedido pedido = pedidoRepository.getOne(pedidoId);
       pedido.getComentarios().add(comentario);
       
    }
    
}
