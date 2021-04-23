package com.okta.aycPedidos.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.okta.aycPedidos.converters.UsuarioConverter;
import com.okta.aycPedidos.entidades.Usuario;
import com.okta.aycPedidos.modelos.UsuarioModel;

@Controller
@RequestMapping("/")
public class InicioController {

	@Autowired
	private HttpSession session;
	@Autowired
	private UsuarioConverter usuarioConverter;

	@PreAuthorize("hasRole('ROLE_ADMIN')||hasRole('ROLE_VENDEDOR')||hasRole('ROLE_DISENADOR')")
	@GetMapping("/inicio")
	public String inicio(ModelMap modelo) {
		
		try {
			UsuarioModel usuario = usuarioConverter.entityToModel((Usuario) session.getAttribute("usuarioSession"));
			modelo.put("nombre", usuario.getUsername());
			modelo.put("rol", usuario.getRol().toString());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			modelo.put("error", ex.getMessage());
			return this.inicio(modelo);
		}

		return "inicio.html";
	}

}
