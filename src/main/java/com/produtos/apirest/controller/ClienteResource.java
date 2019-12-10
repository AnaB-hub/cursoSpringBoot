package com.produtos.apirest.controller;

import java.util.Collections;
import java.util.Comparator;
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

import com.produtos.apirest.models.Cliente;
import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ClienteRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Clientes")
@CrossOrigin(origins="*")
public class ClienteResource {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	@ApiOperation(value="Lista de Clientes")
	public List<Cliente> clientes() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/clientesAtivos")
	@ApiOperation(value="Lista de Clientes ativos")
	public Stream<Cliente> clientesAtivos() {
		List<Cliente> clientes = clienteRepository.findAll();
        Collections.sort(clientes, Comparator.comparing(Cliente::getNome));
		return clientes.stream().filter(c -> c.isAtivo() == true);
	}
	
	@GetMapping("/cliente/{id}")
	@ApiOperation(value="Lista de Clientes")
	public Cliente cliente(@PathVariable(value="id") int id) {
		return this.clienteRepository.findById(id);
	}
	
	@PostMapping("/cliente")
	@ApiOperation(value="Cadastro de Clientes")
	public Cliente saveCliente(@RequestBody Cliente cliente) {
		cliente.setAtivo(true);
		return clienteRepository.save(cliente);
	}
	
	@PutMapping("/cliente")
	@ApiOperation(value="Alteração de Clientes")
	public Cliente updateCliente(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@DeleteMapping("/cliente")
	@ApiOperation(value="Alteração de Clientes")
	public void excluirCliente(@RequestBody Cliente cliente) {
		this.clienteRepository.delete(cliente);
	}
	
	@DeleteMapping("/cliente/excluir/{id}")
	@ApiOperation(value="Exclusão física de Cliente por ID")
	public void deleteCliente(@PathVariable(value="id") int id) {
		clienteRepository.deleteById(id);
	}
	
	@GetMapping("/cliente/excluir/{id}")
	@ApiOperation(value="Exclusão lógica de Cliente por ID")
	public Cliente deleteLogicCliente(@PathVariable(value="id") int id) {
		Cliente cliente = clienteRepository.findById(id);
		cliente.setAtivo(false);
		return clienteRepository.save(cliente);
	}

}
