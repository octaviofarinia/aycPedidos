package com.okta.aycPedidos.controllers;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.okta.aycPedidos.converters.ImagenConverter;
import com.okta.aycPedidos.entidades.Imagen;
import com.okta.aycPedidos.enums.CodigoInterior;
import com.okta.aycPedidos.enums.Estado;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.PedidoModel;
import com.okta.aycPedidos.modelos.UsuarioModel;
import com.okta.aycPedidos.services.ImagenService;
import com.okta.aycPedidos.services.PedidoService;
import com.okta.aycPedidos.services.UsuarioService;

@Controller
@RequestMapping("/")
public class PedidosAdminController {

	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ImagenConverter imagenConverter;
	@Autowired
	private ImagenService imagenService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/pedidosABM")
	public String pedidosABM(ModelMap modelo) {

		try {

			Set<CodigoInterior> codigosInterior = EnumSet.allOf(CodigoInterior.class);
			modelo.put("codigosInterior", codigosInterior);

			List<PedidoModel> pedidos = pedidoService.listarPedidosModelActivos();
			modelo.put("pedidos", pedidos);

			List<UsuarioModel> usuarios = usuarioService.listarUsuariosActivos();
			modelo.put("usuarios", usuarios);

			PedidoModel pedido = new PedidoModel();

			modelo.addAttribute("pedido", pedido);

			return "/Pedidos/pedidosABM_copy.html";

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			modelo.put("error", ex.getMessage());
			return this.pedidosABM(modelo);
		}

	}

	@PostMapping("/registrarPedido")
	public String registrarPedido(ModelMap modelo, @Valid @ModelAttribute("pedido") PedidoModel pedidoModel,
			@RequestParam(required = true) String descripcion, @RequestParam() MultipartFile[] customFondosTapa,
			@RequestParam() MultipartFile[] customFondosContratapa) {

		try {

			List<Imagen> imagenesCustom = new ArrayList<>();
			
			for (MultipartFile multipartFile : customFondosTapa) {
				Imagen imagen = imagenService.guardar(multipartFile);
				imagenesCustom.add(imagen);
			}
			pedidoModel.getAgenda().getTapa().setCustomImagenes(imagenConverter.entitiesToModels(imagenesCustom));
			imagenesCustom.clear();
			
			for (MultipartFile multipartFile : customFondosContratapa) {
				Imagen imagen = imagenService.guardar(multipartFile);
				imagenesCustom.add(imagen);
			}
			pedidoModel.getAgenda().getContratapa().setCustomImagenes(imagenConverter.entitiesToModels(imagenesCustom));
			imagenesCustom.clear();

			pedidoModel.setEstado(Estado.PENDIENTE);
			
			pedidoService.persistir(pedidoModel, descripcion);
			
		} catch (Exception ex) {
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
		} catch (Exception ex) {
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
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			modelo.put("error", ex.getMessage());
			return this.pedidosABM(modelo);
		}
		return "redirect:/pedidosABM";
	}
}
