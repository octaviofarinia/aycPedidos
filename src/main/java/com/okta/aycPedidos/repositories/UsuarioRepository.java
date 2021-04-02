package com.okta.aycPedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.okta.aycPedidos.entidades.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	@Query("SELECT c FROM Usuario c WHERE c.mail =:mail")
    public Usuario buscarPorMail(@Param("mail") String mail);
	
	@Query("SELECT c FROM Usuario c WHERE c.username =:username")
    public Usuario buscarPorUsername(@Param("username") String username);
	
	@Query("SELECT c FROM Usuario c WHERE c.fechaBaja IS NULL")
    public List<Usuario> listarUsuariosActivos();
	
}
