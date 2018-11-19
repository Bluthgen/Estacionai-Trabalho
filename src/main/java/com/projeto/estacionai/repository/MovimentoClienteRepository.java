package com.projeto.estacionai.repository;
import java.time.LocalDate;
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
	
	public MovimentoCliente findFirstByOrderByIdDesc();
	
	@Query(value="SELECT tipo_veiculo, COUNT(tipo_veiculo) FROM movimento_cliente GROUP BY tipo_veiculo;", nativeQuery= true)
	public List<Tuple> buscarMaisUtilizaramEstacionamento();
	
	@Query(value="SELECT tipo_veiculo, COUNT(tipo_veiculo) FROM movimento_cliente WHERE data_movimento BETWEEN " + 
			"?1 AND ?2 GROUP BY tipo_veiculo;", nativeQuery= true)
	public List<Tuple> buscarMaisUtilizaramEstacionamentoComData(LocalDate inicio, LocalDate fim);
	
	@Query(value="SELECT c.nome, COUNT(mv.cliente_id) FROM movimento_cliente AS mv, cliente AS c " + 
			"WHERE mv.cliente_id = c.id " + 
			"GROUP BY mv.cliente_id ;", nativeQuery= true)
	public List<Tuple> buscarClientesMaisUtilizaramEstacionamento();
	
	@Query(value="SELECT c.nome, COUNT(mv.cliente_id) FROM movimento_cliente AS mv, cliente AS c  WHERE mv.cliente_id = c.id AND mv.data_movimento BETWEEN  ?1 AND ?2 GROUP BY mv.cliente_id;", nativeQuery= true)
	public List<Tuple> buscarClientesMaisUtilizaramEstacionamentoComData(LocalDate inicio, LocalDate fim);

}