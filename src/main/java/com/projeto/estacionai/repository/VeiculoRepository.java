package com.projeto.estacionai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.estacionai.model.Veiculo;
 
/**
 * 
 * @author ALISSON 
 *
 */

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	
	public List<Veiculo> findByPlacaLike(String placa);
	
	public List<Veiculo> findByCliente(Long id);
	
	public List<Veiculo> findByAtivoTrue();

}
