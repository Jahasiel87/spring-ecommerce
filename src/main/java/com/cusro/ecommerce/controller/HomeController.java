package com.cusro.ecommerce.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cusro.ecommerce.model.Producto;
import com.cusro.ecommerce.service.IProductoService;

@Controller
@RequestMapping("/")
public class HomeController {

	private final Logger log=LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private IProductoService productoService;
	
	
	@GetMapping("")
	public String home(Model model) {
		
		model.addAttribute("productos", productoService.findAll());
		
		return "usuario/home_usuario";
	}
	
	@GetMapping("productoHome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		
		log.info("Id producto enviado como parametro {}", id);
		
		Producto producto = new Producto();
		
		Optional<Producto> productoOptional= productoService.get(id);
		
		producto = productoOptional.get();
		
		model.addAttribute("producto", producto);
		
		return "usuario/producto_home";
	}
}
