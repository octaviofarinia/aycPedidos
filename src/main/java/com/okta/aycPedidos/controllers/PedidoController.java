package com.okta.aycPedidos.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.okta.aycPedidos.converters.UsuarioConverter;
import com.okta.aycPedidos.entidades.Pedido;
import com.okta.aycPedidos.entidades.Usuario;
import com.okta.aycPedidos.enums.TipoComentario;
import com.okta.aycPedidos.excepciones.WebException;
import com.okta.aycPedidos.modelos.ComentarioModel;
import com.okta.aycPedidos.modelos.ImagenModel;
import com.okta.aycPedidos.modelos.PedidoModel;
import com.okta.aycPedidos.services.ComentarioService;
import com.okta.aycPedidos.services.PedidoService;
import com.okta.aycPedidos.services.UsuarioService;

@Controller
@RequestMapping("/")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioConverter usuarioConverter;
	@Autowired
	private ComentarioService comentarioService;
	@Autowired
    private HttpSession session;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')||hasRole('ROLE_VENDEDOR')||hasRole('ROLE_DISENADOR')")
	@GetMapping("/pedidoInfo/{pedidoId}")
	public String pedidoInfo(ModelMap modelo, @PathVariable String pedidoId) {
		
		try {
			
			PedidoModel pedido = pedidoService.getOneByIdMODEL(Long.parseLong(pedidoId));
			ComentarioModel descripcion = comentarioService.buscarDescripcionPorPedidoMODEL(pedido);
			
			List<ImagenModel> tapaImagenes = pedido.getAgenda().getTapa().getCustomImagenes();
			List<ImagenModel> contratapaImagenes = pedido.getAgenda().getContratapa().getCustomImagenes();
			
			
			List<ComentarioModel> comentarios = comentarioService.listarComentariosPorPedidoMODELS(pedido);
			
			//ordena la lista para que los comentarios se muestren en orden de creacion
			Collections.sort(comentarios, new Comparator<ComentarioModel>() {
				  public int compare(ComentarioModel o1, ComentarioModel o2) {
				      return o1.getFechaAlta().compareTo(o2.getFechaAlta());
				  }
			});
			
			Usuario usuario = (Usuario) session.getAttribute("usuarioSession");
			
			modelo.addAttribute("usuario", usuario);
			modelo.addAttribute("pedido", pedido);
			modelo.addAttribute("tapaImagenes", tapaImagenes);
			modelo.addAttribute("contratapaImagenes", contratapaImagenes);
			modelo.addAttribute("descripcion", descripcion);
			modelo.addAttribute("comentarios", comentarios);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return "redirect:/pedidoInfo/"+pedidoId+"";
		}
		
		return "/Pedidos/pedidoInfo_copy.html";
	}
	
	@PostMapping("/registrarComentario")
	public String registrarComentario(ModelMap modelo,
			@RequestParam(required = true) String contenido,
			@RequestParam(required = true) String pedidoId,
			@RequestParam(required = true) String usuarioId) throws WebException {
		
		Long pedidoIdLong = Long.parseLong(pedidoId);
		Pedido pedido = pedidoService.getOneById(pedidoIdLong);
		
		Usuario usuario = usuarioConverter.modelToEntity(usuarioService.buscarPorId(usuarioId));
		
		comentarioService.registrarComentario(pedido, usuario, contenido, TipoComentario.COMENTARIO);
		
		return "redirect:/pedidoInfo/"+pedidoId+"";
	}
	
	@PostMapping("/hardDeleteComentario")
	public String hardDeleteComentario (ModelMap modelo, 
			@RequestParam(required = true) String comentarioId, 
			@RequestParam(required = true) String pedidoId){
		
		try {
			
			comentarioService.hardDeleteComentario(comentarioId);
			
		}catch (Exception ex) {
			return "redirect:/pedidoInfo/"+pedidoId+"?error=" + ex.getMessage();
		}
		
		return "redirect:/pedidoInfo/"+pedidoId+"";
	}
	
}
