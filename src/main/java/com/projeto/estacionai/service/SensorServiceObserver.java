/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.estacionai.model.Sensor;
import com.projeto.estacionai.repository.SensorRepositoryObserver;

/**
 *
 * @author ALISSON
 */
@Service
public class SensorServiceObserver {
	
	@Autowired
	private SensorRepositoryObserver repository;
	
	public SensorServiceObserver()
	{
		this.repository = new SensorRepositoryObserver();
	}
	
	 public List<Sensor> buscarTodos()
	 {
		 return this.repository.buscarTodos();
	 }
	
   
}
