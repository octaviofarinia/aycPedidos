package com.okta.aycPedidos.controllers;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.okta.aycPedidos.entidades.Pedido;
import com.okta.aycPedidos.enums.CodigoInterior;
import com.okta.aycPedidos.services.AgendaService;
import com.okta.aycPedidos.services.PedidoService;
import com.okta.aycPedidos.services.TapaService;
import com.okta.aycPedidos.services.UsuarioService;

@Controller
@RequestMapping("/")
public class PedidosAdminController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private AgendaService agendaService;
	
	@Autowired
	private TapaService tapaService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/pedidosABM")
    public String pedidosABM(ModelMap modelo) {
		
		Set<CodigoInterior> codigosInterior = EnumSet.allOf(CodigoInterior.class);
        modelo.put("codigosInterior", codigosInterior);
		
        List<Pedido> pedidos = pedidoService.listarPedidos();
        modelo.put("pedidos", pedidos);
        
        return "/Pedidos/pedidosABM.html";
    }
	
	@PostMapping("/registrarPedido")
	public String registrarPedido(ModelMap modelo,
			@RequestParam(required = true) String cantidad,
			@RequestParam(required = true) String nombreCliente,
			@RequestParam(required = true) String codigoInterior,
			@RequestParam(required = true) String comentario,
			//TAPA
			@RequestParam(required = true) String codigoFondoTapa,
			@RequestParam(required = true) MultipartFile customFondoTapa,
			@RequestParam(required = true) String codigoFraseTapa,
			@RequestParam(required = true) String customFraseTapa,
			//CONTRATAPA
			@RequestParam(required = true) String codigoFondoContratapa,
			@RequestParam(required = true) MultipartFile customFondoContratapa,
			@RequestParam(required = true) String codigoFraseContratapa,
			@RequestParam(required = true) String customFraseContratapa) {
		
		try {
			
			pedidoService.registrarPedido(Integer.parseInt(cantidad), nombreCliente, 
					//Estado
					"PENDIENTE", 
					//Vendedor
					null, 
					//Disenador
					null, 
					//Muestra
					null, 
					//Agenda
					agendaService.registrarAgenda(codigoInterior, 
							//Tapa
							tapaService.registrarTapa(codigoFondoTapa, codigoFraseTapa, customFondoTapa, customFraseTapa), 
							//Contratapa
							tapaService.registrarTapa(codigoFondoContratapa, codigoFraseContratapa, customFondoContratapa, customFraseContratapa)));
			
			
			
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
			modelo.put("cantidad", cantidad);
			modelo.put("nombreCliente", nombreCliente);
			modelo.put("codigoInterior", codigoInterior);
			modelo.put("comentario", comentario);
			modelo.put("codigoFondoTapa", customFraseTapa);
			modelo.put("customFondoTapa", customFraseTapa);
			modelo.put("codigoFraseTapa", customFraseTapa);
			modelo.put("customFraseTapa", customFraseTapa);
			modelo.put("codigoFondoContratapa", customFraseContratapa);
			modelo.put("customFondoContratapa", customFraseContratapa);
			modelo.put("codigoFraseContratapa", customFraseContratapa);
			modelo.put("customFraseContratapa", customFraseContratapa);
			modelo.put("error", ex.getMessage());
			return this.pedidosABM(modelo);
		}
		
		return "redirect:/pedidosABM";
	}
}
