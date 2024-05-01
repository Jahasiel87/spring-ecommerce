package com.cusro.ecommerce.service;

import java.util.Optional;

import com.cusro.ecommerce.model.Usuario;

public interface IUsuarioService {
	
	Optional<Usuario> findById(Integer id);
	
	Usuario save(Usuario usuario);

}
