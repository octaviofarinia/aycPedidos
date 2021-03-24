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
import org.springframework.web.multipart.MultipartFile;

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
            @RequestParam(required = true) String nombreCliente,
            @RequestParam(required = true) String codigoInterior,
            @RequestParam(required = true) String codigoFondoTapa,
            @RequestParam(required = true) String codigoFraseTapa,
            @RequestParam(required = true) String fraseCustomTapa,
            @RequestParam(required = true) String codigoFondoContratapa,
            @RequestParam(required = true) String codigoFraseContratapa,
            @RequestParam(required = true) String fraseCustomContratapa,
            @RequestParam(required = true) String cantidad,
            @RequestParam(required = true) String comentario,
            MultipartFile fondoCustomTapa,
            MultipartFile fondoCustomContratapa) {

        try{
            
            
            
        }catch(Exception e){
            
            
            
        }
        
        return "redirect:/cargarPedido";
    }

}
