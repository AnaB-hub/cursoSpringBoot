package com.produtos.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.apirest.models.Compra;
import com.produtos.apirest.repository.CompraRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Compras")
@CrossOrigin(origins = "*")
public class CompraResource {
	
	@Autowired
	CompraRepository compraRepository;
	
	@GetMapping("/compras")
	@ApiOperation(value="Lista de compras")
	private List<Compra> compras(){
		return this.compraRepository.findAll();
	}
	
	@GetMapping("/compra/{id}")
	@ApiOperation(value="Compra por ID")
	private Compra compra(@PathVariable(value="id")int id) {
		return this.compraRepository.findById(id);
	}
	
	@PostMapping("/compra")
	@ApiOperation(value="Cadastro de compras")
	private Compra saveCompra(@RequestBody Compra compra) {
		//Mudar o status
		return this.compraRepository.save(compra);
	}
	
	@PutMapping("/compra")
	@ApiOperation(value="Alteração de compras")
	private Compra updateCompra(@RequestBody Compra compra) {
		return this.compraRepository.save(compra);
	}

}
