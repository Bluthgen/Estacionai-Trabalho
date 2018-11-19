package com.projeto.estacionai.observer;


import com.projeto.estacionai.model.Vaga;
import com.projeto.estacionai.service.VagaService;
import com.projeto.estacionai.service.VagaServiceObserver;

public class VagaObserver extends SensorObserver {
	
	private VagaServiceObserver service;

	public VagaObserver(SensorSujeito sensor) {
		super(sensor);
		this.service = new VagaServiceObserver();
	}

	@Override
	public void atualizar() {
	
		
		//atualiza o estado da vaga de acordo com a mudança do sensor
		Vaga vaga = service.buscar(sensor.pegarEstado().getVaga().getId().intValue());
		System.out.println("Vaga modificada: " + vaga.getId());
		System.out.println("Ocupada antigo: " + vaga.getOcupada());
		if(sensor.pegarEstado().isLigada())
		{
			vaga.setOcupada(true);
		}
		else
		{
			vaga.setOcupada(false);
		}	
		System.out.println("Ocupada novo: " + vaga.getOcupada());
		this.service.salvar(vaga);
		
	}

}
