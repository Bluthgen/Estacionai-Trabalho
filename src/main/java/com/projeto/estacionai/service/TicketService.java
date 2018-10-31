/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.estacionai.model.Ticket;
import com.projeto.estacionai.repository.TicketRepository;

/**
 *
 * @author ALISSON
 */
@Service
public class TicketService {
	
	@Autowired
	private TicketRepository repository;
	
	public Ticket buscarTicket(Long id)
	{
		return this.repository.getOne(id);
	}
	
	public Ticket buscarTicket(String placa)
	{
		return this.repository.findFirstByPlacaOrderByIdDesc(placa);
	}
	
	public void gerarTicket(Ticket ticket)
	{
		this.repository.save(ticket);	
	}
	
	public Ticket buscarUltimo()
	{
		return this.repository.findFirstByOrderByIdDesc();
	}
	
	public void validarTicket(Ticket ticket)
	{
		this.repository.save(ticket);
	}
	
//	public void validarTicket(String placa)
//	{
//		Ticket ticket = repository.findLastByPlacaLike(placa);
//		ticket.setAtivo(true);
//		LocalDateTime horarioSaida = LocalDateTime.now();
//		ticket.setHorarioSaida(horarioSaida);
//		ticket.setTotal(calcularTotal(ticket));
//		
//		//atualiza com o horario de saida e total a pagar
//		repository.save(ticket);
//	}
	
	public Double calcularTotal(Ticket ticket)
	{
				
		
		Double tempoGasto = Double.parseDouble(String.valueOf(Duration.between(ticket.getHorarioChegada(), ticket.getHorarioSaida()).toMinutes()));
				
		//calcula o total a pagar
		if(tempoGasto <= 60)
		{
			return 7.0;
		}
		else if(tempoGasto <= 120)
		{
			return 13.0;
		}
		else
		{		
			return (13.0 + (1.75 * ( (tempoGasto - 120) / 15.0)));
		}
		
		
	}
   
}
