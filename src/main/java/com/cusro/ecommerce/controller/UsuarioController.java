package com.cusro.ecommerce.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cusro.ecommerce.model.Usuario;
import com.cusro.ecommerce.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	
	private final Logger logger=LoggerFactory.getLogger(UsuarioController.class);
	
	@GetMapping("/registro")
	public String create() {
		
		return "usuario/registro";
	}
	
	@PostMapping("/save")
	public String save(Usuario usuario) {
		
		logger.info("Usuario registro: {}", usuario);
		
		usuario.setTipo("USER");
		
		usuarioService.save(usuario);
		
		return "redirect:/";
	}
}
