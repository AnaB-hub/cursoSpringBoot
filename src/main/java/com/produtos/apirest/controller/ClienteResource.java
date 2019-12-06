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

import com.produtos.apirest.models.Cliente;
import com.produtos.apirest.repository.ClienteRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Clientes")
@CrossOrigin(origins="*")
public class ClienteResource {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public List<Cliente> clientes() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/clientesAtivos")
	public Stream<Cliente> clientesAtivos() {
		return clienteRepository.findAll().stream().filter(c -> c.isAtivo() == true);
	}
	
	@GetMapping("cliente/{id}")
	public Cliente cliente(@PathVariable(value="id") int id) {
		return this.clienteRepository.findById(id);
	}
	
	@PostMapping("/cliente")
	public Cliente saveCliente(@RequestBody Cliente cliente) {
		cliente.setAtivo(true);
		return clienteRepository.save(cliente);
	}
	
	@PutMapping("/cliente")
	public Cliente updateCliente(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@DeleteMapping("/cliente/excluir/{id}")
	public void deleteCliente(@PathVariable(value="id") int id) {
		clienteRepository.deleteById(id);
	}
	
	@GetMapping("/cliente/excluir/{id}")
	public Cliente deleteLogicCliente(@PathVariable(value="id") int id) {
		Cliente cliente = clienteRepository.findById(id);
		cliente.setAtivo(false);
		return clienteRepository.save(cliente);
	}

}
