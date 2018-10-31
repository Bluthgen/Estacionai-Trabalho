/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.estacionai.model.MovimentoCliente;
import com.projeto.estacionai.repository.MovimentoClienteRepositoryObserver;

/**
 *
 * @author ALISSON
 */
@Service
public class MovimentoClienteServiceObserver {
	
	@Autowired
	private MovimentoClienteRepositoryObserver repository;
	
	public MovimentoClienteServiceObserver()
	{
		this.repository = new MovimentoClienteRepositoryObserver();
	}
	
	 public void salvar(MovimentoCliente movimentoc)
	 {
		 	movimentoc.setAtivo(true);
	        this.repository.save(movimentoc);
	 }
	
   
}