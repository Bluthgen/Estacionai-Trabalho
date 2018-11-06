package com.projeto.estacionai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.estacionai.model.Mensalidade;
import com.projeto.estacionai.repository.MensalidadeRepository;

@Service
public class MensalidadeService {

	@Autowired
	private MensalidadeRepository repository;
	
	public void salvar(Mensalidade mensalidade)
	{
		mensalidade.setAtivo(true);
		this.repository.save(mensalidade);
	}
	
	public void deletar(Mensalidade mensalidade)
	{
		mensalidade.setAtivo(false);
		this.repository.save(mensalidade);
	}
	
	public void deletar(Long id)
	{
		this.repository.deleteById(id);
	}
	
	public List<Mensalidade> buscarTodos()
	{
		return this.repository.findByAtivoTrue();
	}
	
	public Mensalidade buscar(Long id)
	{
		return this.repository.getOne(id);
	}
	
	
}
