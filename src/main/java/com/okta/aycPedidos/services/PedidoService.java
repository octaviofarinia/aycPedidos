package com.okta.aycPedidos.services;

import com.okta.aycPedidos.entities.Agenda;
import com.okta.aycPedidos.entities.Comentario;
import com.okta.aycPedidos.entities.Pedido;
import com.okta.aycPedidos.entities.Usuario;
import com.okta.aycPedidos.enums.Estado;
import com.okta.aycPedidos.repositories.PedidoRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @Autowired
    private AgendaService agendaService;


    @Transactional
    public void validarPedido(Integer cantidad, String estado, Agenda agenda, Usuario vendedor, Comentario comentario) throws Exception {
        if (cantidad <= 0) {
            throw new Exception("la cantidad debe ser mayor o igual a 1");
        }
    }

    @Transactional
    public void registrarPedido(String nombreCliente, Integer cantidad, String descripcion, String estado, Agenda agenda, Usuario vendedor, Comentario comentario) throws Exception {

        validarPedido(cantidad, estado, agenda, vendedor, comentario);

        Pedido pedido = new Pedido();

        pedido.setNombreCliente(nombreCliente);
        pedido.setCantidad(cantidad);
        pedido.setFechaEmision(new Date());
        pedido.setEstado(Estado.valueOf(estado));
        
        
        pedido.setAgenda(agenda);
        
        
        pedido.setVendedor(vendedor);
        
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
