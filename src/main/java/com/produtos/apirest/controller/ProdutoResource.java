package com.produtos.apirest.controller;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ProdutoRepository;

@RestController
@RequestMapping(value="/api")
public class ProdutoResource {

	@Autowired
	ProdutoRepository produtoRepository;

	@GetMapping("/produtos")
	public List<Produto> listaProdutos() {
		return produtoRepository.findAll();
	}
	
	@GetMapping("/produtosAtivos")
	public Stream<Produto> produtosAtivos() {
		List<Produto> produtos = produtoRepository.findAll();
		return produtos.stream().filter(p -> p.isAtivo() == true);
	}
	
	@GetMapping("/produto/{id}")
	public Produto listaProdutoUnico(@PathVariable(value="id") long id) {
		return produtoRepository.findById(id);
	}
	
	@PostMapping("/produto")
	public Produto salvarProduto(@RequestBody Produto produto) {
		produto.setAtivo(true);
		return produtoRepository.save(produto);
	}
	
	//Delete apresentado (exclui recebendo o objeto)
//	@DeleteMapping("/produto")
//	public void deletaProduto(@RequestBody Produto produto) {
//		produtoRepository.delete(produto);;
//	}
	
	//Exclusão pelo Id
	@DeleteMapping("/produto/{id}")
	public void deletaProduto(@PathVariable(value="id") long id) {
		produtoRepository.deleteById(id);
	}
	
	//Exclusão lógica
	@GetMapping("/produto/excluir/{id}")
	public void excluir(@PathVariable("id") long id) {
		Produto produto = this.produtoRepository.findById(id);

		produto.setAtivo(false);
		this.produtoRepository.save(produto);
	}
	
	@PutMapping("/produto")
	public Produto atualizaProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}


}
