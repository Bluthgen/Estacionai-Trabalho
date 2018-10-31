package com.projeto.estacionai.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projeto.estacionai.model.HistoricoEntradaSaida;
import com.projeto.estacionai.service.HistoricoEntradaSaidaServiceObserver;

@Component
public class EntradaSaidaObserver extends TicketObserver {

	@Autowired
	private HistoricoEntradaSaidaServiceObserver service;
	
	public EntradaSaidaObserver(TicketSujeito ess) {
		super(ess);
		this.service = new HistoricoEntradaSaidaServiceObserver();
	}

	@Override
	public void atualizar() {
	
		HistoricoEntradaSaida hes = new HistoricoEntradaSaida(ess.pegarEstado().getCodigo(), ess.pegarEstado().getHorarioChegada(), ess.pegarEstado().getHorarioSaida());		
		this.service.salvar(hes);
		
	}

}