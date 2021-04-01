package com.okta.aycPedidos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.okta.aycPedidos.services.PedidoService;

@Controller
@RequestMapping("/")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;

	@PreAuthorize("hasRole('ROLE_ADMIN')||hasRole('ROLE_VENDEDOR')||hasRole('ROLE_DISENADOR')")
	@GetMapping("/pedidoInfo/{pedidoId}")
	public String pedidoInfo(ModelMap modelo, @PathVariable String pedidoId) {
		
		modelo.put("pedido", pedidoService.getOneById(Long.parseLong(pedidoId)));
		
		return "/Pedidos/pedidoInfo.html";
	}
	
	
}
