package com.okta.aycPedidos.controllers;

import com.okta.aycPedidos.entities.Usuario;
import com.okta.aycPedidos.repositories.UsuarioRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author octav
 */
@Controller
@RequestMapping("/")
public class InicioController {
    
    @Autowired
    private HttpSession session;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping("/inicio")
    public String login(ModelMap modelo) {
        
        Usuario usuario = usuarioRepository.buscarPorUsername(((Usuario) session.getAttribute("usuarioSession")).getUsername());
        
        modelo.put("nombre", usuario.getUsername());
        modelo.put("rol", usuario.getRol().toString());
        
        return "inicio.html";
    }
    
}
