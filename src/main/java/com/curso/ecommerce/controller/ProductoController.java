package com.curso.ecommerce.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.IProductoService;
import com.curso.ecommerce.service.IUsuarioService;
import com.curso.ecommerce.service.UploadFileService;


@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	private final Logger logger=LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private UploadFileService upload;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("")
	public String show(Model model) {
		
		model.addAttribute("productos", productoService.findAll());
		
		return "productos/show";
	}
	
	@GetMapping("/create")
	public String create() {
		
		return "productos/create";
	}
	
	@PostMapping("/save")
	public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		
		logger.info("Este es el objeto del producto {}", producto);
		
		Usuario usuario=usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

		
		producto.setUsuario(usuario);
		
		//Imagen
		
		if(producto.getId()==null) {  //Cuando se crea un producto
			
			String nombreImagen=upload.saveImage(file);
			
			producto.setImagen(nombreImagen);			
			
		}
		
		productoService.save(producto);
		
		return "redirect:/productos";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		
		Producto producto=new Producto();
		
		Optional<Producto> optionalProducto=productoService.get(id);
		
		producto=optionalProducto.get();
		
		logger.info("Producto buscado: {}", producto);
		
		model.addAttribute("producto", producto);
		
		return "productos/edit";
	}
	
	@PostMapping("/update")
	public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
		
		Producto p=new Producto();
		
		p=productoService.get(producto.getId()).get();

		if(file.isEmpty()) {  //Cuando editamos el producto pero no cambiamos la imagen
			
						
			producto.setImagen(p.getImagen());
			
	}else{  //Cuando editamos  la imagen
				
				
		//Eliminar cuando no sea la imagen por defecto
		if(!p.getImagen().equals("default.jpg")) {
			
			upload.deleteImage(p.getImagen());
		}
		
		String nombreImagen=upload.saveImage(file);
		
		producto.setImagen(nombreImagen);
		
		}
		
		producto.setUsuario(p.getUsuario());

		productoService.update(producto);
		
		return "redirect:/productos";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		Producto p=new Producto();
		
		p=productoService.get(id).get();
		
		
		//Eliminar cuando no sea la imagen por defecto
		if(!p.getImagen().equals("default.jpg")) {
			upload.deleteImage(p.getImagen());
		}
		

		productoService.delete(id);
		
		return "redirect:/productos";
	}

}
