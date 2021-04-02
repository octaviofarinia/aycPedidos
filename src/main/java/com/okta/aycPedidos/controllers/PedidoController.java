package com.okta.aycPedidos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.okta.aycPedidos.entidades.Comentario;
import com.okta.aycPedidos.entidades.Imagen;
import com.okta.aycPedidos.entidades.Pedido;
import com.okta.aycPedidos.services.ComentarioService;
import com.okta.aycPedidos.services.PedidoService;

@Controller
@RequestMapping("/")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ComentarioService comentarioService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')||hasRole('ROLE_VENDEDOR')||hasRole('ROLE_DISENADOR')")
	@GetMapping("/pedidoInfo/{pedidoId}")
	public String pedidoInfo(ModelMap modelo, @PathVariable String pedidoId) {
		
		Pedido pedido = pedidoService.getOneById(Long.parseLong(pedidoId));
		Comentario descripcion = comentarioService.buscarDescripcionPorPedido(pedido);
		
		//De momento solo hay una imagen para la tapa y una para la contratapa
		Imagen tapaImagen = (Imagen) pedido.getAgenda().getTapa().getCustomImagenes().get(0);
		Imagen contratapaImagen = (Imagen) pedido.getAgenda().getContratapa().getCustomImagenes().get(0);
		
		List<Comentario> comentarios = comentarioService.listarComentariosPorPedido(pedido);
		
		modelo.put("pedido", pedido);
		modelo.put("tapaImagen", tapaImagen);
		modelo.put("contratapaImagen", contratapaImagen);
		modelo.put("descripcion", descripcion);
		modelo.put("comentarios", comentarios);
		
		return "/Pedidos/pedidoInfo.html";
	}
	
	
}
