package com.projeto.estacionai;

import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.projeto.estacionai.model.Vaga;
import com.projeto.estacionai.repository.VagaRepositorySearch;
import com.projeto.estacionai.service.VagaService;

public class VagaTeste extends EstacionaiApplicationTests{

	@Autowired
	private VagaService service;
	
	@Autowired
	private VagaRepositorySearch search;
	
	@Test
	public void buscarVagaPorId() {
		Long id= 17L;
		
		Vaga vaga= service.buscar(id);
		Assertions.assertThat(vaga.getId()).isEqualTo(id);
	}
	
	@Test
	public void buscarVagasAtivas() {
		List<Vaga> lista= service.buscarTodos();
		
		for(Vaga vaga : lista) {
			Assertions.assertThat(vaga.getAtivo()).isEqualTo(true);
		}
	}
	
	@Test
	public void buscarVagasDesocupadas() {
		List<Vaga> lista= service.buscarTodosDesocupadas();
		
		for(Vaga vaga : lista) {
			Assertions.assertThat(vaga.getOcupada()).isEqualTo(false);
		}
	}
	
	@Test
	@Transactional
	public void remover() {
		Long id= 22L;
		Vaga vaga1= service.buscar(id);
		service.deletar(id);
		Vaga vaga2= service.buscar(id);
		
		Assertions.assertThat(vaga2.getAtivo()).isEqualTo(false);
	}
	
	@Test
	@Transactional
	public void atualizar() {
		Long id= 22L;
		Vaga vaga= service.buscar(id);
		vaga.setAtivo(true);
		vaga.setOcupada(true);
		vaga.setTipo(1);
		service.salvar(vaga);
		Vaga atualizada= service.buscar(id);
		Assertions.assertThat(atualizada.getAtivo()).isEqualTo(true);
		Assertions.assertThat(atualizada.getOcupada()).isEqualTo(true);
		Assertions.assertThat(atualizada.getTipo()).isEqualTo(1);
		Assertions.assertThat(atualizada.getBloco()).isEqualTo(vaga.getBloco());
	}
	
	@Test
	public void buscarPorFiltro() {
		Vaga vaga= new Vaga();
		vaga.setOcupada(true);
		vaga.setTipo(2);
		
		List<Vaga> lista= search.filtrar(vaga);
		for(Vaga encontrada : lista) {
			Assertions.assertThat(encontrada.getTipo()).isEqualTo(2);
		}
	}
}
