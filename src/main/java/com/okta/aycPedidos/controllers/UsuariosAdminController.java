package com.okta.aycPedidos.controllers;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.okta.aycPedidos.enums.Rol;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.PedidoModel;
import com.okta.aycPedidos.modelos.UsuarioModel;
import com.okta.aycPedidos.services.PedidoService;
import com.okta.aycPedidos.services.UsuarioService;

@Controller
@RequestMapping("/")
public class UsuariosAdminController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PedidoService pedidoService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/usuariosABM")
	public String usuariosABM(ModelMap modelo) throws WebException {

		List<UsuarioModel> usuarios = usuarioService.listarUsuariosActivos();
		modelo.put("usuarios", usuarios);

		Set<Rol> userRoles = EnumSet.allOf(Rol.class);
		modelo.put("roles", userRoles);

		modelo.addAttribute("usuario", new UsuarioModel());
		
		return "/Usuarios/usuariosABM.html";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/usuarioInfo/{usuarioId}")
	public String usuarioInfo(ModelMap modelo, @PathVariable String usuarioId) throws WebException {

		Set<Rol> userRoles = EnumSet.allOf(Rol.class);
		modelo.put("roles", userRoles);

		UsuarioModel usuario = usuarioService.buscarPorId(usuarioId);
		modelo.put("usuario", usuario);

		List<PedidoModel> pedidos = pedidoService.listarPedidosPorUsuario(usuario);
		modelo.put("pedidos", pedidos);

		return "/Usuarios/usuarioInfo.html";
	}

	@PostMapping("/registrarUsuario")
	public String registrarUsuario(ModelMap modelo,
			@Valid @ModelAttribute("usuario") UsuarioModel usuarioModel, 
			@RequestParam(required = true) String password,
			@RequestParam(required = true) String repeated_password) throws Exception {

		try {
			usuarioService.persistir(usuarioModel, password, repeated_password);
		} catch (Exception ex) {
			return "redirect:/usuariosABM?error=" + ex.getMessage();
		}

		return "redirect:/usuariosABM";
	}

	@PostMapping("/modificarUsuario")
	public String modificarUsuario(ModelMap modelo,
			@Valid @ModelAttribute("usuario") UsuarioModel usuarioModel, 
			@RequestParam(required = true) String password,
			@RequestParam(required = true) String repeated_password) throws Exception {

		try {
			usuarioService.persistir(usuarioModel, password, repeated_password);
		} catch (Exception ex) {
			return "redirect:/usuariosABM?error=" + ex.getMessage();
		}

		return "redirect:/usuariosABM";
	}

	@PostMapping("/hardDeleteUsuario")
	public String hardDeleteUsuario(ModelMap modelo, @RequestParam(required = true) String usuarioId) throws Exception {

		try {
			usuarioService.hardDeleteUsuario(usuarioId);
		} catch (Exception ex) {
			return "redirect:/usuariosABM?error=" + ex.getMessage();
		}

		return "redirect:/usuariosABM";
	}

}
