package com.produtos.apirest.controller;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.apirest.models.Fornecedor;
import com.produtos.apirest.repository.FornecedorRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Fornecedores")
@CrossOrigin(origins = "*")
public class FornecedorResource {

	@Autowired
	FornecedorRepository fornecedorRepository;

	@GetMapping("/fornecedores")
	@ApiOperation(value="Lista de Fornecedores")
	public List<Fornecedor> fornecedores() {
		return this.fornecedorRepository.findAll();
	}

	@GetMapping("/fornecedores-ativos")
	@ApiOperation(value="Lista de Fornecedores ativos")
	public Stream<Fornecedor> fornecedoresAtivos() {
		return this.fornecedorRepository.findAll().stream().filter(f -> f.isAtivo() == true);
	}

	@PostMapping("/fornecedor")
	@ApiOperation(value="Cadastro de Fornecedor")
	public Fornecedor saveFornecedor(@RequestBody Fornecedor fornecedor) {
		fornecedor.setAtivo(true);
		return this.fornecedorRepository.save(fornecedor);
	}

	@PutMapping("/fornecedor")
	@ApiOperation(value="Alteração Fornecedor")
	public Fornecedor updateFornecedor(@RequestBody Fornecedor fornecedor) {
		return this.fornecedorRepository.save(fornecedor);
	}

	// Exclusão lógica
	@GetMapping("/fornecedor/excluir/{id}")
	@ApiOperation(value="Exclusão lógica")
	public Fornecedor exclusaoFornecedor(@PathVariable(value = "id") int id) {
		Fornecedor fornecedor = this.fornecedorRepository.findById(id);
		fornecedor.setAtivo(false);
		return this.fornecedorRepository.save(fornecedor);
	}

	// Exclusão física por ID
	@DeleteMapping("/fornecedor/{id}")
	@ApiOperation(value="Exclusão física por ID")
	public void deleteFornecedor(@PathVariable(value = "id") int id) {
		this.fornecedorRepository.deleteById(id);
	}

	// Exclusão física por objeto
	@DeleteMapping("/fornecedor")
	@ApiOperation(value="Exclusão física por OBJETO")
	public void deleteFornecedorPorObjeto(@RequestBody Fornecedor fornecedor) {
		this.fornecedorRepository.delete(fornecedor);
	}

}
