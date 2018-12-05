package com.projeto.estacionai;

import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.projeto.estacionai.model.Funcionario;
import com.projeto.estacionai.repository.FuncionarioRepositorySearch;
import com.projeto.estacionai.service.FuncionarioService;

public class FuncionarioTeste extends EstacionaiApplicationTests{

	@Autowired
	private FuncionarioService service;
	
	@Autowired
	private FuncionarioRepositorySearch search;
	
	@Test
	public void buscarFuncionarioPorId() {
		Long id= 8L;
		
		Funcionario funcionario= service.buscar(id);
		Assertions.assertThat(funcionario.getId()).isEqualTo(id);
	}
	
	@Test
	public void buscarFuncionarioPorNome() {
		String nome= "user1";
		
		Funcionario funcionario= service.buscarUser(nome);
		Assertions.assertThat(funcionario.getLogin()).isEqualTo(nome);
	}
	
	@Test
	@Transactional
	public void remover() {
		Long id= 103L;
		
		Funcionario funcionario1= service.buscar(id);
		service.deletar(funcionario1);
		Funcionario funcionario2= service.buscar(id);
		Assertions.assertThat(funcionario2.getAtivo()).isEqualTo(false);
	}
	
	@Test
	@Transactional
	public void atualizar() {
		Long id= 103L;
		
		Funcionario funcionario= service.buscar(id);
		funcionario.setAtivo(true);
		funcionario.setCpf("987.654.321-00");
		funcionario.setFuncao("Atualizado");
		service.salvar(funcionario);
		Funcionario atualizado= service.buscar(funcionario.getId());
		Assertions.assertThat(atualizado.getId()).isEqualTo(funcionario.getId());
		Assertions.assertThat(atualizado.getCpf()).isEqualTo(funcionario.getCpf());
		Assertions.assertThat(atualizado.getFuncao()).isEqualTo(funcionario.getFuncao());
		Assertions.assertThat(atualizado.getTelefone()).isEqualTo(funcionario.getTelefone());
	}
	
	@Test
	public void buscarPorFiltro() {
		Funcionario funcionario= new Funcionario();
		funcionario.setCpf("222.222.222-22");
		funcionario.setLogin("user10");
		
		List<Funcionario> lista= search.filtrar(funcionario);
		
		for(Funcionario encontrado : lista) {
			Assertions.assertThat("222.222.222-22").isEqualTo(encontrado.getCpf());
			Assertions.assertThat("user10").isEqualTo(encontrado.getLogin());
		}
	}
}
