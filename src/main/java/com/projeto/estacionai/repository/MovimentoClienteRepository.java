package com.projeto.estacionai.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto.estacionai.model.MovimentoCliente;

/**
 * 
 * @author Alisson
 *
 */

@Repository
public interface MovimentoClienteRepository extends JpaRepository<MovimentoCliente, Long> {
	
	public List<MovimentoCliente> findByAtivoTrue();

}