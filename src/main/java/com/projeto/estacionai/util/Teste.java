package com.projeto.estacionai.util;

import java.time.Duration;
import java.time.LocalDateTime;

import com.projeto.estacionai.model.Ticket;
import com.projeto.estacionai.service.TicketService;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Ticket esta = new Ticket();
		esta.setHorarioSaida(LocalDateTime.now().plusMinutes(150));
		esta.setHorarioChegada(LocalDateTime.now());
		Double tempoGasto = Double.parseDouble(String.valueOf(Duration.between(esta.getHorarioChegada(), esta.getHorarioSaida()).toMinutes()));	
		System.out.println(new TicketService().calcularTotal(tempoGasto));;

	}

}
