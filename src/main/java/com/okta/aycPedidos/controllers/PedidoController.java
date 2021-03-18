package com.okta.aycPedidos.controllers;

import com.okta.aycPedidos.entities.Usuario;
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
public class PedidoController {
    
    @GetMapping("/cargarPedido")
    public String login(ModelMap modelo) {
        
        return "cargar_pedido.html";
    }
    
}
