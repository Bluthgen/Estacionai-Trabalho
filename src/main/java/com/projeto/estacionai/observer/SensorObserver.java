package com.projeto.estacionai.observer;

public abstract class SensorObserver {
	
	protected SensorSujeito sensor;
	 
    public SensorObserver(SensorSujeito sensor) {
        this.sensor = sensor;
    }
	
	public abstract void atualizar();

}
