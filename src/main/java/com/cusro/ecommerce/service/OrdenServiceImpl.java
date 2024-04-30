package com.cusro.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cusro.ecommerce.model.Orden;
import com.cusro.ecommerce.repository.IOrdenRepository;

@Service
public class OrdenServiceImpl implements IOrdenService {

	@Autowired
	private IOrdenRepository ordenRepository;
	
	@Override
	public Orden save(Orden orden) {

		return ordenRepository.save(orden);
	}

	@Override
	public List<Orden> findAll() {

		return ordenRepository.findAll();
	}

}
