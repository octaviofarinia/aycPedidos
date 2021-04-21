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
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.UsuarioModel;
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
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/pedidosABM")
    public String pedidosABM(ModelMap modelo) throws WebException {
		Set<CodigoInterior> codigosInterior = EnumSet.allOf(CodigoInterior.class);
        modelo.put("codigosInterior", codigosInterior);
        
        List<Pedido> pedidos = pedidoService.listarPedidosActivos();
        modelo.put("pedidos", pedidos);
        
        List<UsuarioModel> usuarios = usuarioService.listarUsuariosActivos();
        modelo.put("usuarios", usuarios);
        
        return "/Pedidos/pedidosABM.html";
    }
	
	@PostMapping("/registrarPedido")
	public String registrarPedido(ModelMap modelo,
			@RequestParam(required = true) String cantidad,
			@RequestParam(required = true) String nombreCliente,
			@RequestParam(required = true) String codigoInterior,
			@RequestParam(required = true) String descripcion,
			@RequestParam(required = true) String vendedorId,
			@RequestParam(required = true) String disenadorId,
			//TAPA
			@RequestParam(required = true) String codigoFondoTapa,
			@RequestParam() MultipartFile[] customFondosTapa,
			@RequestParam(required = true) String codigoFraseTapa,
			@RequestParam() String customFraseTapa,
			//CONTRATAPA
			@RequestParam(required = true) String codigoFondoContratapa,
			@RequestParam() MultipartFile[] customFondosContratapa,
			@RequestParam(required = true) String codigoFraseContratapa,
			@RequestParam() String customFraseContratapa) throws WebException {
		
		try {
			pedidoService.registrarPedido(descripcion, Integer.parseInt(cantidad), nombreCliente, 
					//Estado
					"PENDIENTE", 
					//Vendedor
					vendedorId, 
					//Disenador
					disenadorId, 
					//Muestra
					null, 
					//Agenda
					agendaService.registrarAgenda(codigoInterior, 
							//Tapa
							tapaService.registrarTapa(codigoFondoTapa, codigoFraseTapa, customFondosTapa, customFraseTapa), 
							//Contratapa
							tapaService.registrarTapa(codigoFondoContratapa, codigoFraseContratapa, customFondosContratapa, customFraseContratapa)));	
			
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
			modelo.put("error", ex.getMessage());
			return this.pedidosABM(modelo);
		}
		return "redirect:/pedidosABM";
	}
	
	@PostMapping("/hardDeletePedido")
	public String hardDeletePedido(ModelMap modelo, String pedidoId) throws WebException {
		try {
			pedidoService.hardDeletePedido(Long.parseLong(pedidoId));
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
			modelo.put("error", ex.getMessage());
			return this.pedidosABM(modelo);
		}
		return "redirect:/pedidosABM";
	}
	
	@PostMapping("/softDeletePedido")
	public String softDeletePedido(ModelMap modelo, String pedidoId) throws WebException {
		try {
			pedidoService.softDeletePedido(Long.parseLong(pedidoId));
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
			modelo.put("error", ex.getMessage());
			return this.pedidosABM(modelo);
		}
		return "redirect:/pedidosABM";
	}
}
