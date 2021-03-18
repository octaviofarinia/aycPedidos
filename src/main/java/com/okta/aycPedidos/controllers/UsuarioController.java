package com.okta.aycPedidos.controllers;

import com.okta.aycPedidos.entities.Usuario;
import com.okta.aycPedidos.enums.Rol;
import com.okta.aycPedidos.services.UsuarioService;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/usuarios_ABM")
    public String usuariosABM(ModelMap modelo) {
        
        Set<Rol> userRoles = EnumSet.allOf(Rol.class);
        modelo.put("roles", userRoles);
        
        List<Usuario> usuariosLista = usuarioService.listarUsuarios();
        modelo.put("usuarios", usuariosLista);
        
        return "abm_usuarios.html";
    }
    
    @PostMapping("/alta_usuario")
    public String altaUsuario(ModelMap modelo,
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String password,
            @RequestParam(required = true) String mail,
            @RequestParam(required = true) String repeated_password,
            @RequestParam(required = true) String rol) throws Exception {

        try {
            usuarioService.registrarUsuario(username, mail, password, repeated_password, Rol.valueOf(rol));
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            return this.usuariosABM(modelo);
        }
        return "redirect:/usuarios_ABM";
    }
    
    @PostMapping("/baja_usuario")
    public String bajaUsuario(ModelMap modelo,
            @RequestParam(required = true) String idUsuario) throws Exception {

        try {
            usuarioService.eliminarUsuario(idUsuario);
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            return this.usuariosABM(modelo);
        }
        return "redirect:/usuarios_ABM";
    }
    
}
