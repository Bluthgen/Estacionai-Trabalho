package com.projeto.estacionai.repository;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
	
	@Query(value="SELECT tipo_veiculo, COUNT(tipo_veiculo) FROM movimento_cliente GROUP BY tipo_veiculo;", nativeQuery= true)
	public List<Tuple> buscarMaisUtilizaramEstacionamento();

}