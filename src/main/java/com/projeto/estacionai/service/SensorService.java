/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.service;

import com.projeto.estacionai.model.Sensor;
import com.projeto.estacionai.model.Vaga;
import com.projeto.estacionai.repository.SensorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alisson
 */
@Service
public class SensorService {
    @Autowired
    private SensorRepository repository;
	
    public void salvar(Sensor sensor)
    {
    	sensor.setAtivo(true);
        this.repository.save(sensor);
    }
    
    public void deletar(Sensor sensor)
    {
    	sensor.setAtivo(false);
        this.repository.save(sensor);
    }
	
    public void deletar(Long id)
    {
        this.repository.deleteById(id);
    }
	
    public List<Sensor> buscarTodos()
    {
        return this.repository.findByAtivoTrue();
    }
    
    public List<Sensor> buscarTodosDesligados()
    {
        return null;
    }
    
    public Integer quantidadeSensoresLigados()
    {
    	return this.repository.countSensoresLigados();
    }
	
    public Sensor buscar(Long id)
    {
        return this.repository.getOne(id);
    }
    
    public List<Sensor> buscarVagasBloco()
    {
    	return null;
    }
}

