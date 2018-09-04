package com.projeto.estacionai.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto.estacionai.model.Funcionario;

/**
 * 
 * @author Eduardo
 *
 */

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	public List<Funcionario> findByAtivoTrue();
	
}