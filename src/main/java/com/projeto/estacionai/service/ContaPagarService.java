/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.estacionai.model.ContaEquipamento;
import com.projeto.estacionai.model.ContaPagar;
import com.projeto.estacionai.repository.ContaPagarRepository;

/**
 *
 * @author ALISSON
 */
@Service
public class ContaPagarService {
	
	@Autowired
	private ContaPagarRepository repository;
	
	public void salvar (ContaPagar conta) {
		conta.setAtivo(true);
		this.repository.save(conta);
	}
	
	public void deletar (ContaPagar conta) {
		conta.setAtivo(false);
		this.repository.save(conta);
	}
	
	public void deletar(Long id)
	{
		this.repository.deleteById(id);
	}
	
	public List<ContaPagar> buscarTodos()
	{
		return this.repository.findByAtivoTrue();
	}
	
	public ContaPagar buscar(Long id)
	{
		return this.repository.getOne(id);
	}
	
	public Double total()
	{
		return this.repository.getTotal();
	}
   
}
