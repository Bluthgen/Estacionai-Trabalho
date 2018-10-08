/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.service;

import com.projeto.estacionai.model.Permissao;
import com.projeto.estacionai.repository.PermissaoRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alisson
 */
@Service
public class PermissaoService {
	
    @Autowired
    private PermissaoRepository repository;
	
    public void salvar(Permissao fp)
    {
        this.repository.save(fp);
    }
	
    public void deletar(Long id)
    {
        this.repository.deleteById(id);
    }
	
    public List<Permissao> buscarTodos()
    {
        return this.repository.findAll();
    }
    
    public Permissao buscar(Long id)
    {
        return this.repository.getOne(id);
    }	
}