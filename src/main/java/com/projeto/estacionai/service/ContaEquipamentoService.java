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
import com.projeto.estacionai.repository.ContaEquipamentoRepository;
import com.projeto.estacionai.repository.ContaPagarRepository;

/**
 *
 * @author ALISSON
 */
@Service
public class ContaEquipamentoService {
	
	@Autowired
	private ContaEquipamentoRepository repository;
	
	public void salvar (ContaEquipamento contaEquipamento) {
		contaEquipamento.setAtivo(true);
		this.repository.save(contaEquipamento);
	}
	
	public void deletar (ContaEquipamento contaEquipamento) {
		contaEquipamento.setAtivo(false);
		this.repository.save(contaEquipamento);
	}
	
	public void deletar(Long id)
	{
		this.repository.deleteById(id);
	}
	
	public List<ContaEquipamento> buscarTodos()
	{
		return this.repository.findByAtivoTrue();
	}
	
	public ContaEquipamento buscar(Long id)
	{
		return this.repository.getOne(id);
	}
	
	public Double total()
	{
		return this.repository.getTotal();
	}
   
}
