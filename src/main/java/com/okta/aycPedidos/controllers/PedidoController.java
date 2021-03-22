package com.okta.aycPedidos.controllers;

import com.okta.aycPedidos.enums.CodigoProducto;
import com.okta.aycPedidos.enums.Rol;
import java.util.EnumSet;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author octav
 */
@Controller
@RequestMapping("/")
public class PedidoController {
    
    @GetMapping("/cargarPedido")
    public String login(ModelMap modelo) {
                    
        Set<CodigoProducto> codigosInterior = EnumSet.allOf(CodigoProducto.class);
        modelo.put("codigosInterior", codigosInterior);
        
        return "indexNuevoPedido.html";
    }
    
    @PostMapping("/registrarPedido")
    public String registrarPedido(ModelMap modelo,
            @RequestParam(required = true) String nombreCliente) {
        
            
        
        return "redirect:/cargarPedido";
    }
    
}
