package com.okta.aycPedidos.controllers;

import java.util.EnumSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.okta.aycPedidos.enums.Rol;
import com.okta.aycPedidos.services.UsuarioService;

@Controller
@RequestMapping("/")
public class RegistroController {

	@Autowired
    private UsuarioService usuarioService;
	
	//@Autowired
	//private UsuarioController usuariosABM;
	
	@GetMapping("/registro")
    public String registro(ModelMap modelo) {
		Set<Rol> userRoles = EnumSet.allOf(Rol.class);
        modelo.put("roles", userRoles);
        return "registro.html";
    }
	
	@PostMapping("/registrarUsuario")
    public String registrarUsuario(ModelMap modelo,
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String password,
            @RequestParam(required = true) String mail,
            @RequestParam(required = true) String repeated_password,
            @RequestParam(required = true) String rol) throws Exception{
		
		try {
			usuarioService.registrarUsuario(username, mail, password, repeated_password, Rol.valueOf(rol));
		}catch(Exception ex) {
			modelo.put("error", ex.getMessage());
            //return usuarioController.usuariosABM(modelo);
		}
		
        return "redirect:/login";
    }
	
}
