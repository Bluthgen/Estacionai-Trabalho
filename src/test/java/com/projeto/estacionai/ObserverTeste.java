package com.projeto.estacionai;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.projeto.estacionai.model.HistoricoEntradaSaida;
import com.projeto.estacionai.model.MovimentoCliente;
import com.projeto.estacionai.model.Ticket;
import com.projeto.estacionai.observer.ClienteMovimentoObserver;
import com.projeto.estacionai.observer.EntradaSaidaObserver;
import com.projeto.estacionai.observer.TicketSujeito;
import com.projeto.estacionai.service.HistoricoEntradaSaidaService;
import com.projeto.estacionai.service.MovimentoClienteService;
import com.projeto.estacionai.service.TicketService;

public class ObserverTeste extends EstacionaiApplicationTests {
	
	@Autowired
	private TicketSujeito sujeito;
	
	@Autowired
	private TicketService service;
	@Autowired
	private HistoricoEntradaSaidaService entradaService;
	@Autowired
	private MovimentoClienteService movimentoService;
	
	private Ticket ticket;
	
	public ObserverTeste() {
		// TODO Auto-generated constructor stub
		if(sujeito == null)
			this.sujeito = new TicketSujeito();
	}
	
	
	@Test
	public void buscarTicket()
	{
		String placa = "asd-2342";
		this.ticket = this.service.buscarTicket(placa);
	    Assertions.assertThat(ticket.getPlaca()).isEqualTo(placa);
	}
	
	@Test
	public void atualizarTotal()
	{
		if(this.ticket == null)
		{
			String placa = "asd-2342";
			this.ticket = this.service.buscarTicket(placa);
		}
		
		this.ticket.setHorarioSaida(LocalDateTime.now());
		Double tempoGasto = 90.0;	
		Double total = this.service.calcularTotal(tempoGasto);
		
		Assertions.assertThat(total).isEqualTo(13.0);
		this.ticket.setTotal(total);
		
		this.service.validarTicket(ticket);
	}
	
	
	@Test
	public void buscarUltimoTicket()
	{
		Ticket ultimo  = this.service.buscarUltimo();
		ultimo.setTipoVeiculo(2);
		
		ticket = ultimo;
		String placa = "asd-2342";
	    Assertions.assertThat(this.ticket.getPlaca()).isEqualTo(placa);		
	}
	
	@Test
	public void anexar()
	{
		if(this.ticket == null)
		{
			this.ticket = this.service.buscarUltimo();
		}
		
		if(sujeito == null)
			this.sujeito = new TicketSujeito();
		
		this.sujeito.getObservadores().clear();
		//anexando e alertando os observadores
		this.sujeito.anexar(new EntradaSaidaObserver(this.sujeito));
		this.sujeito.anexar(new ClienteMovimentoObserver(this.sujeito));
		
		
		Assertions.assertThat(this.sujeito.getObservadores().get(0).getClass().getName()).isEqualTo("com.projeto.estacionai.observer.EntradaSaidaObserver");
		Assertions.assertThat(this.sujeito.getObservadores().get(1).getClass().getName()).isEqualTo("com.projeto.estacionai.observer.ClienteMovimentoObserver");		
		
	}
	
	@Test
	public void verificar()
	{
		if(this.ticket == null)
		{
			this.ticket = this.service.buscarUltimo();
		}
		
		String codigo = ticket.getCodigo();
		HistoricoEntradaSaida hes = entradaService.buscarUltimo();
		MovimentoCliente mc = movimentoService.buscarUltimo();
		
		Assertions.assertThat(hes.getCodigo()).isEqualTo(codigo);
		Assertions.assertThat(mc.getNome()).isEqualTo(codigo);			
		
	}
	
	
	
	
	
	
	

}
