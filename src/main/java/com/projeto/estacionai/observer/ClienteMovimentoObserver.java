package com.projeto.estacionai.observer;



import org.springframework.beans.factory.annotation.Autowired;

import com.projeto.estacionai.model.MovimentoCliente;
import com.projeto.estacionai.service.MovimentoClienteServiceObserver;
public class ClienteMovimentoObserver extends TicketObserver {
	
	@Autowired
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