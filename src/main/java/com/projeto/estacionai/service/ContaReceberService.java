/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.estacionai.model.ContaReceber;
import com.projeto.estacionai.repository.ContaReceberRepository;

/**
 *
 * @author ALISSON
 */
@Service
public class ContaReceberService {
	
	@Autowired
	private ContaReceberRepository repository;
	
	public void salvar (ContaReceber conta) {
		conta.setAtivo(true);
		this.repository.save(conta);
	}
	
	public void deletar(ContaReceber conta) {
		conta.setAtivo(false);
		this.repository.save(conta);
	}
	
	public void deletar(Long id)
	{
		this.repository.deleteById(id);
	}
	
	public List<ContaReceber> buscarTodos()
	{
		return this.repository.findByAtivoTrue();
	}
	
	public ContaReceber buscar(Long id)
	{
		return this.repository.getOne(id);
	}
	
	public Double total()
	{
		return this.repository.getTotal();
	}
   
}
