package com.projeto.estacionai.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.estacionai.model.MovimentoCliente;
import com.projeto.estacionai.relatorio.RelatorioMovimentoTipo1;
import com.projeto.estacionai.relatorio.RelatorioMovimentoTipo3;
import com.projeto.estacionai.repository.MovimentoClienteRepository;

/**
 * 
 * @author Alisson
 *
 */

@Service
public class MovimentoClienteService {

	@Autowired
	private MovimentoClienteRepository repository;
	
	public void salvar (MovimentoCliente movimento) {
		movimento.setAtivo(true);
		this.repository.save(movimento);
	}
	
	public void deletar (MovimentoCliente movimento) {
		movimento.setAtivo(false);
		this.repository.save(movimento);
	}
	
	public void deletar(Long id)
	{
		this.repository.deleteById(id);
	}
	
	public List<MovimentoCliente> buscarTodos()
	{
		return this.repository.findByAtivoTrue();
	}
	
	public MovimentoCliente buscar(Long id)
	{
		return this.repository.getOne(id);
	}
	
	
	public List<RelatorioMovimentoTipo1> buscarMaisUtilizaramEstacionamento()
	{
		List<Tuple> resultado = this.repository.buscarMaisUtilizaramEstacionamento();
		List<RelatorioMovimentoTipo1> lista = new ArrayList<>();
		
		for(Tuple result : resultado)
		{
			lista.add(new RelatorioMovimentoTipo1(Integer.valueOf(result.get(0).toString()), Integer.valueOf(result.get(1).toString())));
		}
		
		
		return lista;
	}
	
	public List<RelatorioMovimentoTipo3> buscarClientesMaisUtilizaramEstacionamento()
	{
		List<Tuple> resultado = this.repository.buscarClientesMaisUtilizaramEstacionamento();
		List<RelatorioMovimentoTipo3> lista = new ArrayList<>();
		
		for(Tuple result : resultado)
		{
			lista.add(new RelatorioMovimentoTipo3(result.get(0).toString(), Integer.valueOf(result.get(1).toString())));
		}
		
		
		return lista;
	}
}