package com.cusro.ecommerce.service;

import java.util.List;

import com.cusro.ecommerce.model.Orden;

public interface IOrdenService {
	
	List<Orden> findAll();

	Orden save(Orden orden);
}
