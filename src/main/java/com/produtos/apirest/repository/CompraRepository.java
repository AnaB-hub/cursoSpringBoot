package com.produtos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtos.apirest.models.Compra;

public interface CompraRepository extends JpaRepository<Compra, Integer>{
	
	Compra findById(int id);

}
