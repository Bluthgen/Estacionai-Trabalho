package com.projeto.estacionai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.estacionai.model.Mensalidade;

/**
 * 
 * @author Guilherme 
 *
 */

@Repository
public interface MensalidadeRepository extends JpaRepository<Mensalidade, Long>{

	public List<Mensalidade> findByidCliente(Long id);
	
	public List<Mensalidade> findByAtivoTrue();
	
}
