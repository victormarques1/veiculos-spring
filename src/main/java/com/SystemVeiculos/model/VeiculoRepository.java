package com.SystemVeiculos.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VeiculoRepository extends CrudRepository<Veiculo, Long> {
	List<Veiculo> findByModelo(String modelo);
}

