/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.service;

import com.projeto.estacionai.model.Bloco;
import com.projeto.estacionai.repository.BlocoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eduardo
 */
@Service
public class BlocoService {
    @Autowired
    private BlocoRepository repository;
	
    public void salvar(Bloco bloco)
    {
    	bloco.setAtivo(true);
        this.repository.save(bloco);
    }
    
    public void deletar(Bloco bloco)
    {
    	bloco.setAtivo(false);
        this.repository.save(bloco);
    }
	
    public void deletar(Long id)
    {
        this.repository.deleteById(id);
    }
	
    public List<Bloco> buscarTodos()
    {
        return this.repository.findByAtivoTrue();
    }

    public Bloco buscarUltimo()
    {
        return this.repository.findFirstByOrderByIdDesc();
    }
    
    public Bloco buscar(Long id)
    {
        return this.repository.getOne(id);
    }	
}
