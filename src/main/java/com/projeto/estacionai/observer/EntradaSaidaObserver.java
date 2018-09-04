package com.projeto.estacionai.observer;

import org.springframework.beans.factory.annotation.Autowired;

import com.projeto.estacionai.model.HistoricoEntradaSaida;
import com.projeto.estacionai.service.HistoricoEntradaSaidaService;

public class EntradaSaidaObserver extends TicketObserver {
	
	private HistoricoEntradaSaidaService service;

	public EntradaSaidaObserver(TicketSujeito ess) {
		super(ess);
		this.service = new HistoricoEntradaSaidaService();
	}

	@Override
	public void atualizar() {
		
		HistoricoEntradaSaida hes;
		if(ess.pegarEstado().getHorarioSaida() != null)
		{
			hes = new HistoricoEntradaSaida(ess.pegarEstado().getCodigo(), ess.pegarEstado().getHorarioChegada(), ess.pegarEstado().getHorarioSaida());
		}
		else
		{
			hes = new HistoricoEntradaSaida(ess.pegarEstado().getCodigo(), ess.pegarEstado().getHorarioChegada(), null);
		}
		
		this.service.salvar(hes);
		
	}

}
