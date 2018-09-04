package com.projeto.estacionai.observer;

public abstract class TicketObserver {
	
	protected TicketSujeito ess;
	 
    public TicketObserver(TicketSujeito ess) {
        this.ess = ess;
    }
	
	public abstract void atualizar();

}
