package com.projeto.estacionai.observer;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.projeto.estacionai.model.Sensor;

@Component
public class SensorSujeito {
	
	private ArrayList<SensorObserver> observadores;
	private Sensor sensor;
	
	
	public SensorSujeito()
	{
		observadores = new ArrayList<SensorObserver>();
	}
	
	public void anexar(SensorObserver observador)
	{
		observadores.add(observador);
	}
	
	public void desanexar(int indice)
	{
		observadores.remove(indice);
	}
	
	public void setarEstado(Sensor sensor) {
	    this.sensor = sensor;
	    notifyObservers();
	}
	 
	private void notifyObservers() {
	    for (SensorObserver observador : observadores) {
	    	observador.atualizar();
	    }
	}
	 
	public Sensor pegarEstado() {
	    return sensor;
	}
	
	public ArrayList<SensorObserver> getObservadores() {
		return observadores;
	}

}
