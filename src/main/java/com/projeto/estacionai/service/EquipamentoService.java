package com.projeto.estacionai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.estacionai.model.Equipamento;
import com.projeto.estacionai.repository.EquipamentoRepository;

@Service
public class EquipamentoService {
	@Autowired
    private EquipamentoRepository repository;
	
    public void salvar(Equipamento equipamento)
    {
    	equipamento.setAtivo(true);
        this.repository.save(equipamento);
    }
    
    public void deletar(Equipamento equipamento)
    {
    	equipamento.setAtivo(false);
        this.repository.save(equipamento);
    }
	
	
    public List<Equipamento> buscarTodos()
    {
        return this.repository.findByAtivoTrue();
    }


    public Equipamento buscar(Long id)
    {
        return this.repository.getOne(id);
    }	
}
