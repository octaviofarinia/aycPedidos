package com.okta.aycPedidos.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.okta.aycPedidos.entidades.Usuario;
import com.okta.aycPedidos.enums.Rol;
import com.okta.aycPedidos.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
    public void registrarUsuario(String username, String mail, String password, String repeatedPass, Rol rol) throws Exception {

        validarDatosUsuario(username, mail, password, repeatedPass, rol);

        Usuario usuario = new Usuario();

        usuario.setUsername(username);
        
        String encriptada = new BCryptPasswordEncoder().encode(password);
        usuario.setPassword(encriptada);
        
        usuario.setMail(mail);
        
        usuario.setRol(rol);
        
        usuario.setFechaAlta(new Date());
        usuario.setFechaBaja(null);
        usuario.setFechaModificacion(null);

        usuarioRepository.save(usuario);
    }
	
	@Transactional
    public void modificarUsuario(String id, String username, String mail, String password, String repeatedPass, Rol rol) throws Exception {

        validarDatosUsuario(username, mail, password, repeatedPass, rol);

        Optional<Usuario> respuesta = usuarioRepository.findById(id);

        if (respuesta.isPresent()) {
        	Usuario usuario = respuesta.get();

            usuario.setUsername(username);
            
            String encriptada = new BCryptPasswordEncoder().encode(password);
            usuario.setPassword(encriptada);
            
            usuario.setMail(mail);
            
            usuario.setRol(rol);
            
            usuario.setFechaModificacion(new Date());

            usuarioRepository.save(usuario);
        } else {
            throw new Exception("No se encontro el usuario");
        }

    }
	
	@Transactional
    public void hardDeleteUsuario(String id) throws Exception {

        Optional<Usuario> respuesta = usuarioRepository.findById(id);

        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();

            usuarioRepository.delete(usuario);

        } else {
            throw new Exception("No se encontro el usuario solicitado");
        }
    }
	
	@Transactional
    public void softDeleteUsuario(String id) throws Exception {

        Optional<Usuario> respuesta = usuarioRepository.findById(id);

        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();

            usuario.setFechaBaja(new Date());
            
            usuarioRepository.save(usuario);
            
        } else {
            throw new Exception("No se encontro el usuario solicitado");
        }
    }
	
	public void validarDatosUsuario(String username, String mail, String password, String repeatedPass, Rol rol) throws Exception {
        if (username == null || username.isEmpty()) {
            throw new Exception("Nombre de usuario invalido");
        }
        if (mail == null || mail.isEmpty()) {
            throw new Exception("Mail invalido");
        }
        if (password == null || password.isEmpty() || password.length() <= 8) {
            throw new Exception("Contraseña invalida, debe tener como minimo 8 caracteres");
        }
        if (!password.equals(repeatedPass)) {
            throw new Exception("Las contraseñas deben ser iguales");
        }
        if (rol == null) {
            throw new Exception("Debe elegir un rol");
        }
    }

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.buscarPorMail(mail);

		if (usuario != null) {

			List<GrantedAuthority> permisos = new ArrayList<>();

			GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
			permisos.add(p1);

			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("usuarioSession", usuario);

			User user = new User(usuario.getUsername(), usuario.getPassword(), permisos);

			return user;

		} else {
			return null;
		}
	}
	
	@Transactional
	public Usuario buscarPorUsername(String username) {
		return usuarioRepository.buscarPorUsername(username);
	}

	@Transactional
	public List<Usuario> listarUsuariosActivos() {
		return usuarioRepository.listarUsuariosActivos();
	}
	
}
