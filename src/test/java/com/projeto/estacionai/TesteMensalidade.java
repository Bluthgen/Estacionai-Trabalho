package com.projeto.estacionai;

import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.projeto.estacionai.model.Mensalidade;
import com.projeto.estacionai.repository.MensalidadeRepositorySearch;
import com.projeto.estacionai.service.MensalidadeService;

public class TesteMensalidade extends EstacionaiApplicationTests{
	
	@Autowired
	private MensalidadeRepositorySearch search;
	
	@Autowired
	private MensalidadeService service;
	
	@Test
	@Transactional
	public void alterarStatus() {
		Mensalidade original = service.buscar(20L);
		
		original.setStatusPago();
		service.salvar(original);
		Mensalidade atualizadoPago = service.buscar(20L);
		Assertions.assertThat(original.getStatus()).isEqualTo(atualizadoPago.getStatus());
		
		original.setStatusAtraso();
		service.salvar(original);
		Mensalidade atualizadoAtraso = service.buscar(20L);
		Assertions.assertThat(original.getStatus()).isEqualTo(atualizadoAtraso.getStatus());
		
		
	}
}
