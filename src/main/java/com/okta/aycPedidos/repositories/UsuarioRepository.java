package com.okta.aycPedidos.repositories;

import com.okta.aycPedidos.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author octav
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query("SELECT c FROM Usuario c WHERE c.username =:username")
    public Usuario buscarPorUsername(@Param("username") String username);
    
    @Query("SELECT c FROM Usuario c WHERE c.mail =:mail")
    public Usuario buscarPorMail(@Param("mail") String mail);

}
