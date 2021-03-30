package com.okta.aycPedidos.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.okta.aycPedidos.entidades.Agenda;
import com.okta.aycPedidos.entidades.Imagen;
import com.okta.aycPedidos.entidades.Pedido;
import com.okta.aycPedidos.entidades.Usuario;
import com.okta.aycPedidos.enums.Estado;
import com.okta.aycPedidos.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ImagenService imagenService;

	@Transactional
	public void registrarPedido(Integer cantidad, String nombreCliente, String estado, Agenda agenda, Usuario vendedor,
			Usuario disenador, MultipartFile imagenMultipartFile) throws Exception {
		
		Pedido pedido = new Pedido();
		
		pedido.setCantidad(cantidad);
		
		pedido.setNombreCliente(nombreCliente);
		
		pedido.setEstado(Estado.valueOf(estado));
		
		pedido.setAgenda(agenda);
		
		pedido.setVendedor(vendedor);
		
		pedido.setDisenador(disenador);
		
		pedido.setFechaAlta(new Date());
		
		pedido.setFechaBaja(null);
		
		pedido.setFechaModificacion(null);
		
		this.agregarPreview(pedido.getId(), imagenMultipartFile);
		
	}
	
	@Transactional
	public void modificarPedido(Long pedidoId, Integer cantidad, String nombreCliente, String estado, Agenda agenda, Usuario vendedor,
			Usuario disenador, MultipartFile imagenMultipartFile) throws Exception {
		
		Optional<Pedido> respuesta = pedidoRepository.findById(pedidoId);

        if (respuesta.isPresent()) {
        	Pedido pedido = respuesta.get();
        	
        	pedido.setCantidad(cantidad);
        	
        	pedido.setNombreCliente(nombreCliente);
        	
        	pedido.setEstado(Estado.valueOf(estado));
        	
        	pedido.setAgenda(agenda);
        	
        	pedido.setVendedor(vendedor);
        	
        	pedido.setDisenador(disenador);
        	
        	pedido.setFechaModificacion(new Date());
        	
        	this.agregarPreview(pedidoId, imagenMultipartFile);       	
        } else {
            throw new Exception("No se encontro el pedido");
        }
		
	}
	
	@Transactional
    public void hardDeletePedido(Long pedidoId) {
        pedidoRepository.deleteById(pedidoId);
    }
	
	@Transactional
    public void softDeletePedido(Long pedidoId) throws Exception {
		Optional<Pedido> respuesta = pedidoRepository.findById(pedidoId);

        if (respuesta.isPresent()) {

            Pedido pedido = respuesta.get();

            pedido.setFechaBaja(new Date());
            
            pedidoRepository.save(pedido);
            
        } else {
            throw new Exception("No se encontro el pedido");
        }
    }
	
	@Transactional
	public void agregarPreview(Long pedidoId, MultipartFile imagenMultipartFile) throws Exception {
		Pedido pedido = pedidoRepository.getOne(pedidoId);
		if (imagenMultipartFile != null) {
			Imagen imagen = imagenService.guardar(imagenMultipartFile);
			pedido.setPreview(imagen);
		}
	}

}
