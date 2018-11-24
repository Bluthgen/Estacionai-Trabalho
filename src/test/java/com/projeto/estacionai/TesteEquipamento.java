package com.projeto.estacionai;

import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.projeto.estacionai.model.Equipamento;
import com.projeto.estacionai.repository.EquipamentoRepositorySearch;
import com.projeto.estacionai.service.EquipamentoService;

public class TesteEquipamento extends EstacionaiApplicationTests {
	
	@Autowired
	private EquipamentoService service;
	
	@Autowired
	private EquipamentoRepositorySearch search;
	
	@Test
	public void buscarEquipamentoId() {
		Long id = 2L;
		Equipamento equipamento = service.buscar(id);
		Assertions.assertThat(equipamento.getId()).isEqualTo(id);
	}
	
	@Test
	@Transactional
	public void deletarEquipamento() {
		Long id = 4L;
		Equipamento original = service.buscar(id);
		service.deletar(original);
		Equipamento deletado = service.buscar(id);
		Assertions.assertThat(deletado.getAtivo()).isEqualTo(false);
	}
	
	@Test
	@Transactional
	public void atualizarEquipamento() {
		Equipamento original = service.buscar(4L);
		original.setAtivo(true);		// Recupera o de id 4 que tinha sido deletado e trás como ativo novamente
		original.setDescricao("Atualizado");			// Altera 4 parametros do original
		original.setTipo("TipoAtualizado");
		original.setStatus("StatusAtualizado");
		service.salvar(original);
		Equipamento atualizado = service.buscar(4L);  		// Recupera o mesmo equipamento depois da atualização
		Assertions.assertThat(original.getAtivo()).isEqualTo(atualizado.getAtivo());
		Assertions.assertThat(original.getDescricao()).isEqualTo(atualizado.getDescricao());
		Assertions.assertThat(original.getId()).isEqualTo(atualizado.getId());
		Assertions.assertThat(original.getStatus()).isEqualTo(atualizado.getStatus());
		Assertions.assertThat(original.getTipo()).isEqualTo(atualizado.getTipo());
	}
	
	
	@Test
	public void filtrarEquipamento() {
		Equipamento filtro  = new Equipamento();
		filtro.setTipo("teste");
		List<Equipamento> filtrados = search.filtrar(filtro);
		for(Equipamento e : filtrados) {
			Assertions.assertThat(e.getTipo().toLowerCase()).contains("teste");
		}
		
		
	}
	

}
