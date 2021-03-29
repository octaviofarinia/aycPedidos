package com.okta.aycPedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.okta.aycPedidos.entidades.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, String>{

}
