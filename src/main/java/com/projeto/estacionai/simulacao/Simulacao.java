package com.projeto.estacionai.simulacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projeto.estacionai.model.Sensor;
import com.projeto.estacionai.observer.SensorSujeito;
import com.projeto.estacionai.observer.VagaObserver;
import com.projeto.estacionai.service.SensorServiceObserver;


@Component
public class Simulacao implements Runnable {
	

	@Autowired
	private SensorSujeito sujeito;
	@Autowired
	private SensorServiceObserver service;
	
	List<Sensor> sensores = new ArrayList<>();
	private Random random = new Random();
	
	public Simulacao() {
		
			this.service = new SensorServiceObserver();
			this.sujeito = new SensorSujeito();
			this.sensores = this.service.buscarTodos();
	}

	@Override
	public void run() {
		
		int max = 0;
		while(true)
		{
			try {
				
				Sensor sorteado = getRandomList(sensores);
				System.out.println("Sensor sorteado: " + sorteado.getId());
				//desanexando os que já tem
				this.sujeito.getObservadores().clear();
				//alertando os observadores 
				this.sujeito.anexar(new VagaObserver(sujeito));
				this.sujeito.setarEstado(sorteado);
				
				System.out.println("Simulando vagas...");
				
				
				Thread.sleep(3000);
				max++;
				
				if(max == 10)
				{
					break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
		}
				
				
		
	}

	
	public Sensor getRandomList(List<Sensor> list) {

	    int index = random.nextInt(list.size());
	    return list.get(index);
	    
	}

}
