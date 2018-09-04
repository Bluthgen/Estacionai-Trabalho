package com.projeto.estacionai.observer;


import com.projeto.estacionai.model.Vaga;
import com.projeto.estacionai.service.VagaService;

public class VagaObserver extends SensorObserver {
	
	private VagaService service;

	public VagaObserver(SensorSujeito sensor) {
		super(sensor);
		this.service = new VagaService();
	}

	@Override
	public void atualizar() {
		
		//atualiza o estado da vaga de acordo com a mudança do sensor
		Vaga vaga = service.buscar(sensor.pegarEstado().getVaga().getId());
		if(sensor.pegarEstado().isLigada())
		{
			vaga.setOcupada(true);
		}
		else
		{
			vaga.setOcupada(false);
		}	
		
		this.service.salvar(vaga);
		
	}

}
