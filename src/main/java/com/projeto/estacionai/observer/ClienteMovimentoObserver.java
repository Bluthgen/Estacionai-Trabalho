package com.projeto.estacionai.observer;


import java.time.LocalDate;

import com.projeto.estacionai.model.HistoricoEntradaSaida;
import com.projeto.estacionai.model.MovimentoCliente;
import com.projeto.estacionai.service.HistoricoEntradaSaidaService;
import com.projeto.estacionai.service.MovimentoClienteService;
import com.projeto.estacionai.service.MovimentoClienteServiceObserver;

public class ClienteMovimentoObserver extends TicketObserver {
	
	private MovimentoClienteServiceObserver service;

	public ClienteMovimentoObserver(TicketSujeito ess) {
		super(ess);
		this.service = new MovimentoClienteServiceObserver();
	}

	@Override
	public void atualizar() {
		
		MovimentoCliente mv = new MovimentoCliente(ess.pegarEstado().getCodigo(), ess.pegarEstado().getCliente(), ess.pegarEstado().getTipoVeiculo(), ess.pegarEstado().getHorarioSaida().toLocalDate());
		this.service.salvar(mv);
		
	}

}
