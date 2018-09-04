package com.projeto.estacionai.observer;


import java.time.LocalDate;

import com.projeto.estacionai.model.HistoricoEntradaSaida;
import com.projeto.estacionai.model.MovimentoCliente;
import com.projeto.estacionai.service.HistoricoEntradaSaidaService;
import com.projeto.estacionai.service.MovimentoClienteService;

public class ClienteMovimentoObserver extends TicketObserver {
	
	private MovimentoClienteService service;

	public ClienteMovimentoObserver(TicketSujeito ess) {
		super(ess);
		this.service = new MovimentoClienteService();
	}

	@Override
	public void atualizar() {
		
		MovimentoCliente mv = new MovimentoCliente(ess.pegarEstado().getCodigo(), 2, ess.pegarEstado().getHorarioSaida().toLocalDate());
		this.service.salvar(mv);
		
	}

}
