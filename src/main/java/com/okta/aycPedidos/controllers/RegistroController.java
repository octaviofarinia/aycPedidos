package com.okta.aycPedidos.controllers;

import java.util.EnumSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.okta.aycPedidos.enums.Rol;

@Controller
@RequestMapping("/")
public class RegistroController {
	
	@GetMapping("/registro")
    public String registro(ModelMap modelo) {
		Set<Rol> userRoles = EnumSet.allOf(Rol.class);
        modelo.put("roles", userRoles);
        return "registro.html";
    }
	
}
