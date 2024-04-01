package com.cusro.ecommerce.service;

import java.util.Optional;


import com.cusro.ecommerce.model.Producto;


public interface IProductoService {
	
	public Producto save(Producto producto);
	
	public Optional<Producto> get(Integer id);
	
	public void update(Producto producto);
	
	public void delete(Integer id);


}
