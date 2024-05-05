package com.cusro.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cusro.ecommerce.model.Producto;
import com.cusro.ecommerce.service.IOrdenService;
import com.cusro.ecommerce.service.IProductoService;
import com.cusro.ecommerce.service.IUsuarioService;

@Controller
@RequestMapping("/administrador")
public class AministradorController {
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	IUsuarioService usuarioService;
	
	@Autowired
	private IOrdenService ordenService;
	
	@GetMapping("")
	public String home(Model model) {
		
		List<Producto> productos=productoService.findAll();
	
		model.addAttribute("productos", productos);
		
		return "administrador/home";
	}

	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		
		model.addAttribute("usuarios", usuarioService.findAll());
		
		return "administrador/usuarios";
	}
	
	@GetMapping("/ordenes")
	public String ordenes(Model model) {
		
		model.addAttribute("ordenes", ordenService.findAll());
		
		return "administrador/ordenes";
	}
}
