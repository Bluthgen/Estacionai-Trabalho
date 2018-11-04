package com.projeto.estacionai;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.projeto.estacionai.model.Veiculo;
import com.projeto.estacionai.service.VeiculoService;

public class VeiculoTeste {
	
	
	@Autowired
	private VeiculoService service;
	
	@Test
	public void buscarVeiculoPorId()
	{
		Veiculo veiculo = service.buscar(4L);
		Assertions.assertThat(veiculo.getId()).isEqualTo(4L);
	}
	
	@Test
	public void buscarVeiculoPorPlaca()
	{
		Veiculo veiculo = service.buscarPorPlaca("ASD-3434");
		Assertions.assertThat(veiculo.getPlaca()).isEqualTo("ASD-3434");
	}
	
	@Test
	public void buscarVeiculoPorCliente()
	{
		List<Veiculo> veiculos = service.buscarPorCliente(2L);
		for (Veiculo veiculo : veiculos) {
			Assertions.assertThat(veiculo.getCliente().getId()).isEqualTo(2L);
		}
		
	}
	
	

}
