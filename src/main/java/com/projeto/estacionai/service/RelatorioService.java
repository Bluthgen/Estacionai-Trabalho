/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.service;

import com.projeto.estacionai.model.Relatorio;
import com.projeto.estacionai.repository.RelatorioRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alisson
 */
@Service
public class RelatorioService {
	
    @Autowired
    private RelatorioRepository repository;
	
    public void salvar(Relatorio relatorio)
    {
    	relatorio.setAtivo(true);
        this.repository.save(relatorio);
    }
    
    public void deletar(Relatorio relatorio)
    {
    	relatorio.setAtivo(false);
        this.repository.save(relatorio);
    }
	
    public void deletar(Long id)
    {
        this.repository.deleteById(id);
    }
	
    public List<Relatorio> buscarTodos()
    {
        return this.repository.findByAtivoTrue();
    }
    
    public Relatorio buscar(Long id)
    {
        return this.repository.getOne(id);
    }	
}
