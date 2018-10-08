/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.estacionai.model.HistoricoEntradaSaida;
import com.projeto.estacionai.repository.HistoricoEntradaSaidaRepository;

/**
 *
 * @author ALISSON
 */
@Service
public class HistoricoEntradaSaidaService {
	
	@Autowired
	private HistoricoEntradaSaidaRepository repository;
	
	 public void salvar(HistoricoEntradaSaida historico)
	    {
		 	historico.setAtivo(true);
	        this.repository.save(historico);
	    }
	 
	 public void deletar(HistoricoEntradaSaida historico)
	    {
		 	historico.setAtivo(false);
	        this.repository.save(historico);
	    }
		
	    public void deletar(Long id)
	    {
	        this.repository.deleteById(id);
	    }
		
	    public List<HistoricoEntradaSaida> buscarTodos()
	    {
	        return this.repository.findByAtivoTrue();
	    }
	    
	    public HistoricoEntradaSaida buscar(Long id)
	    {
	        return this.repository.getOne(id);
	    }	
   
}