package com.okta.aycPedidos.services;

import java.util.Date;
import java.util.List;
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
import com.okta.aycPedidos.repositories.AgendaRepository;
import com.okta.aycPedidos.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ImagenService imagenService;
	
	@Autowired
	private AgendaService agendaService;

	@Transactional
	public void registrarPedido(Integer cantidad, String nombreCliente, String estado, Usuario vendedor,
			Usuario disenador, MultipartFile imagenMultipartFile, Agenda agenda) throws Exception {

		Pedido pedido = new Pedido();

		try {

			pedido.setCantidad(cantidad);

			pedido.setNombreCliente(nombreCliente);

			pedido.setEstado(Estado.valueOf(estado));

			pedido.setAgenda(agenda);

			if (vendedor != null) {
				pedido.setVendedor(vendedor);
			} else {
				pedido.setVendedor(null);
			}

			if (disenador != null) {
				pedido.setDisenador(disenador);
			} else {
				pedido.setDisenador(null);
			}

			pedido.setFechaAlta(new Date());

			pedido.setFechaBaja(null);

			pedido.setFechaModificacion(null);

			if (imagenMultipartFile != null) {
				Imagen imagen = imagenService.guardar(imagenMultipartFile);
				pedido.setPreview(imagen);
			} else {
				pedido.setPreview(null);
			}

			pedidoRepository.save(pedido);
			
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

	}

	@Transactional
	public void modificarPedido(Long pedidoId, Integer cantidad, String nombreCliente, String estado, Agenda agenda,
			Usuario vendedor, Usuario disenador, MultipartFile imagenMultipartFile) throws Exception {

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
			
			pedidoRepository.save(pedido);
		} else {
			throw new Exception("No se encontro el pedido");
		}

	}

	@Transactional
	public void hardDeletePedido(Long pedidoId) throws Exception {
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
		pedidoRepository.save(pedido);
	}

	@Transactional
	public Pedido getOneById(Long pedidoId) {
		return pedidoRepository.getOne(pedidoId);
	}
	
	
	@Transactional
	public List<Pedido> listarPedidosActivos() {
		return pedidoRepository.listarPedidosActivos();
	}
}
