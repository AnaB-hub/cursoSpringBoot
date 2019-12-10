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

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ProdutoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Produtos")
@CrossOrigin(origins="*")//dominio que quero que acesse minha API, no caso, todos (swagger)
public class ProdutoResource {

	@Autowired
	ProdutoRepository produtoRepository;

	@GetMapping("/produtos")
	@ApiOperation(value="Retorna uma lista de produtos")
	public List<Produto> listaProdutos() {
		return produtoRepository.findAll();
	}
	
	@GetMapping("/produtosAtivos")
	@ApiOperation(value="Retorna uma lista de produtos ativos")
	public Stream<Produto> produtosAtivos() {
		List<Produto> produtos = produtoRepository.findAll();
		Collections.sort(produtos, Comparator.comparing(Produto::getNome));
		return produtos.stream().filter(p -> p.isAtivo() == true);
	}
	
	@GetMapping("/produto/{id}")
	@ApiOperation(value="Retorna um produto através de seu Id")
	public Produto listaProdutoUnico(@PathVariable(value="id") long id) {
		return produtoRepository.findById(id);
	}
	
	@PostMapping("/produto")
	@ApiOperation(value="Salva um produto")
	public Produto salvarProduto(@RequestBody Produto produto) {
		produto.setAtivo(true);
		return produtoRepository.save(produto);
	}
	
	//Delete apresentado (excluir recebendo o objeto)
	@DeleteMapping("/produto")
	@ApiOperation(value="Exclusão de produto passando um objeto")
	public void deletaProduto(@RequestBody Produto produto) {
		produtoRepository.delete(produto);;
	}
	
	//Exclusão pelo Id
	@DeleteMapping("/produto/{id}")
	@ApiOperation(value="Exclusão de produto passando um Id")
	public void deletaProduto(@PathVariable(value="id") long id) {
		produtoRepository.deleteById(id);
	}
	
	//Exclusão lógica
	@GetMapping("/produto/excluir/{id}")
	@ApiOperation(value="Exclusão logica de produto passando um Id")
	public void excluir(@PathVariable("id") long id) {
		Produto produto = this.produtoRepository.findById(id);

		produto.setAtivo(false);
		this.produtoRepository.save(produto);
	}
	
	@PutMapping("/produto")
	@ApiOperation(value="Alteração de produto")
	public Produto atualizaProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}


}
