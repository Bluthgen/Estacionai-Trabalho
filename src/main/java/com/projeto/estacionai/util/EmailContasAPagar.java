package com.projeto.estacionai.util;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.projeto.estacionai.model.ContaPagar;
import com.projeto.estacionai.service.ContaPagarService;

@Component
@EnableScheduling
public class EmailContasAPagar {

	@Autowired
	private Email email;
	
	@Autowired
	private ContaPagarService service;
	
	private static final String TIME_ZONE = "America/Sao_Paulo";
	
	public EmailContasAPagar() {
		
	}
	
	@Scheduled(cron = "0 0 12 * * *", zone=TIME_ZONE)
	private void checaContas() {
		List<ContaPagar> lista= service.buscarTodos();
		LocalDate dataAtual = LocalDate.now();
		LocalDate dataCincoDias= dataAtual.plusDays(5);
		
		for(ContaPagar conta : lista) {
			if(conta.getDataVencimento().isBefore(dataCincoDias) &&
					conta.getDataVencimento().isAfter(dataAtual)) {
				email.enviarMensagem("estacionaiuem@gmail.com", "Contas Para Vencer!", 
						"A conta " + conta.getNome() + ", no valor de R$" + conta.getValor() +
						" vence no dia " + conta.getDataVencimento().getDayOfMonth()+ "!");
			}else if (conta.getDataVencimento().isBefore(dataAtual)) {
				email.enviarMensagem("estacionaiuem@gmail.com", "Conta Vencida!", 
						"A conta " + conta.getNome() + ", no valor de R$" + conta.getValor() +
						" já venceu no dia " + conta.getDataVencimento().getDayOfMonth() + "!");
			}
		}
	}
}
