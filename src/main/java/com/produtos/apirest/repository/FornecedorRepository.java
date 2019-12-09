package com.produtos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtos.apirest.models.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer>{

	Fornecedor findById(int id);
}
