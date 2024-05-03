package com.cusro.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.cusro.ecommerce.model.Orden;
import com.cusro.ecommerce.model.Usuario;

public interface IOrdenService {
	
	List<Orden> findAll();
	
	Optional<Orden>  findById(Integer id);

	Orden save(Orden orden);
	
	String generarNumeroOrden();
	
	List<Orden> findByUsuario (Usuario usuario);	

}
