package com.okta.aycPedidos.controllers;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.okta.aycPedidos.entidades.Pedido;
import com.okta.aycPedidos.entidades.Usuario;
import com.okta.aycPedidos.enums.Rol;
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
	public String usuariosABM(ModelMap modelo) {

		List<Usuario> usuarios = usuarioService.listarUsuariosActivos();
		modelo.put("usuarios", usuarios);

		Set<Rol> userRoles = EnumSet.allOf(Rol.class);
		modelo.put("roles", userRoles);

		return "/Usuarios/usuariosABM.html";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/usuarioInfo/{usuarioId}")
	public String usuarioInfo(ModelMap modelo, @PathVariable String usuarioId) {

		Set<Rol> userRoles = EnumSet.allOf(Rol.class);
		modelo.put("roles", userRoles);

		Usuario usu = usuarioService.buscarPorId(usuarioId);
		
		List<Pedido> pedidos = pedidoService.listarPedidosPorUsuario(usu);
		modelo.put("pedidos", pedidos);
				
		Usuario usuario = usuarioService.buscarPorId(usuarioId);
		modelo.put("usuario", usuario);

		return "/Usuarios/usuarioInfo.html";
	}

	@PostMapping("/registrarUsuario")
	public String registrarUsuario(ModelMap modelo, @RequestParam(required = true) String username,
			@RequestParam(required = true) String password, @RequestParam(required = true) String mail,
			@RequestParam(required = true) String repeated_password, @RequestParam(required = true) String rol)
			throws Exception {

		try {
			usuarioService.registrarUsuario(username, mail, password, repeated_password, Rol.valueOf(rol));
		} catch (Exception ex) {
			return "redirect:/usuariosABM?error=" + ex.getMessage();
		}

		return "redirect:/usuariosABM";
	}

	@PostMapping("/modificarUsuario")
	public String modificarUsuario(ModelMap modelo, @RequestParam(required = true) String usuarioId,
			@RequestParam(required = true) String username, @RequestParam(required = true) String password,
			@RequestParam(required = true) String mail, @RequestParam(required = true) String repeated_password,
			@RequestParam(required = true) String rol) throws Exception {

		try {
			usuarioService.modificarUsuario(usuarioId, username, mail, password, repeated_password, Rol.valueOf(rol));
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
