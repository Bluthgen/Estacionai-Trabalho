package com.projeto.estacionai;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.projeto.estacionai.model.Cliente;
import com.projeto.estacionai.model.Veiculo;
import com.projeto.estacionai.repository.VeiculoRepositorySearch;
import com.projeto.estacionai.service.VeiculoService;

public class VeiculoTeste extends EstacionaiApplicationTests {
	
	
	@Autowired
	private VeiculoService service;
	@Autowired
	private VeiculoRepositorySearch search;
	
	@Test
	public void buscarVeiculoPorId()
	{
		
		Long id = 4L;
		
		Veiculo veiculo = service.buscar(id);
		Assertions.assertThat(veiculo.getId()).isEqualTo(id);
	}
	
	@Test
	public void buscarVeiculoPorPlaca()
	{
		
		String placa = "ASD-3434";
		
		Veiculo veiculo = service.buscarPorPlaca(placa);
		Assertions.assertThat(veiculo.getPlaca()).isEqualTo(placa);
	}
	
	@Test
	public void buscarVeiculoPorCliente()
	{
		Cliente cliente = new Cliente();
		cliente.setId(2L);
		
		List<Veiculo> veiculos = service.buscarPorCliente(cliente.getId());
		for (Veiculo veiculo : veiculos) {
			Assertions.assertThat(veiculo.getCliente().getId()).isEqualTo(cliente.getId());
		}
		
	}
	
	@Test
	public void remover()
	{
		Long id = 3L;
		Veiculo pesquisado = service.buscar(id);
		
		service.deletar(pesquisado);
		Veiculo veiculo = service.buscar(id);
		Assertions.assertThat(veiculo.getAtivo()).isEqualTo(0);
	}
	
	@Test
	public void atualizar()
	{
		
		Veiculo veiculo = service.buscar(3L);
		veiculo.setAtivo(true);
		veiculo.setModelo("Modelo atualizado");
		veiculo.setMarca("Marca atualizada");
		
		service.salvar(veiculo);
		Veiculo atualizado = service.buscar(veiculo.getId());
		Assertions.assertThat(veiculo.getCliente().getId()).isEqualTo(atualizado.getCliente().getId());
		Assertions.assertThat(veiculo.getAno()).isEqualTo(atualizado.getAno());
		Assertions.assertThat(veiculo.getAtivo()).isEqualTo(atualizado.getAtivo());
		Assertions.assertThat(veiculo.getMarca()).isEqualTo(atualizado.getMarca());
		Assertions.assertThat(veiculo.getModelo()).isEqualTo(atualizado.getModelo());
		Assertions.assertThat(veiculo.getPlaca()).isEqualTo(atualizado.getPlaca());
		Assertions.assertThat(veiculo.getRenavam()).isEqualTo(atualizado.getRenavam());
		Assertions.assertThat(veiculo.getTipo()).isEqualTo(atualizado.getTipo());
		
	}
	
	@Test
	public void buscarEspecifio()
	{
		
		Veiculo veiculo = new Veiculo();
		veiculo.setPlaca("asd-2342");
		veiculo.setAno(2010);
		
		List<Veiculo> veiculos = search.filtrar(veiculo);
		
		for (Veiculo retorno : veiculos) {
			
			Assertions.assertThat(2010).isEqualTo(retorno.getAno());
			Assertions.assertThat("asd-2342").isEqualTo(retorno.getPlaca());
			
		}
		
		
		
	}
	
	
	
	
	

}
