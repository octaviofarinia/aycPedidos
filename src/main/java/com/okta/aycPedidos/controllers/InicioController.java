package com.okta.aycPedidos.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.okta.aycPedidos.entidades.Usuario;

@Controller
@RequestMapping("/")
public class InicioController {

	@Autowired
    private HttpSession session;
    
    @PreAuthorize("hasRole('ROLE_ADMIN')||hasRole('ROLE_VENDEDOR')||hasRole('ROLE_DISENADOR')")
    @GetMapping("/inicio")
    public String inicio(ModelMap modelo) {
    	Usuario usuario = (Usuario) session.getAttribute("usuarioSession");
        
        modelo.put("nombre", usuario.getUsername());
        modelo.put("rol", usuario.getRol().toString());
        
        return "inicio.html";
    }
	
}
