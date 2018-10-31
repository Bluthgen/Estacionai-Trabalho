/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.estacionai.model.HistoricoEntradaSaida;
import com.projeto.estacionai.repository.HistoricoEntradaSaidaRepositoryObserver;

/**
 *
 * @author ALISSON
 */
@Service
public class HistoricoEntradaSaidaServiceObserver {
	
	@Autowired
	private HistoricoEntradaSaidaRepositoryObserver repository;
	
	public HistoricoEntradaSaidaServiceObserver()
	{
		this.repository = new HistoricoEntradaSaidaRepositoryObserver();
	}
	
	 public void salvar(HistoricoEntradaSaida historico)
	 {
		 	historico.setAtivo(true);
	        this.repository.save(historico);
	 }
	
   
}
