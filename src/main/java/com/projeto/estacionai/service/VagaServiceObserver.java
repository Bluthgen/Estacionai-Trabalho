package com.projeto.estacionai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.estacionai.model.Vaga;
import com.projeto.estacionai.repository.VagaRepositoryObserver;

@Service
public class VagaServiceObserver {
	
	@Autowired
	private VagaRepositoryObserver repository;
	
	public VagaServiceObserver()
	{
		this.repository = new VagaRepositoryObserver();
	}
	
	 public void salvar(Vaga vaga)
	 {
	        this.repository.save(vaga);
	 }
	 
	 public Vaga buscar(int id)
	 {
		 return this.repository.buscar(id);
	 }

}
