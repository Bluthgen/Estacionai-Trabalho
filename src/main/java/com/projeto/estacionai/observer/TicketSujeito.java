package com.projeto.estacionai.observer;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.projeto.estacionai.model.Ticket;

@Component
public class TicketSujeito {
	
	private ArrayList<TicketObserver> observadores;	
	private Ticket ticket;
	
	
	public TicketSujeito()
	{
		observadores = new ArrayList<TicketObserver>();
	}
	
	public void anexar(TicketObserver observador)
	{
		observadores.add(observador);
	}
	
	public void desanexar(int indice)
	{
		observadores.remove(indice);
	}
	
	public void setarEstado(Ticket ticket) {
	    this.ticket = ticket;
	    notifyObservers();
	}
	 
	private void notifyObservers() {
	    for (TicketObserver observador : observadores) {
	    	observador.atualizar();
	    }
	}
	 
	public Ticket pegarEstado() {
	    return ticket;
	}

	public ArrayList<TicketObserver> getObservadores() {
		return observadores;
	}
	
	

}