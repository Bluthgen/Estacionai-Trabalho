package com.projeto.estacionai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.estacionai.model.Cliente;
import com.projeto.estacionai.repository.ClienteRepository;

/**
 * 
 * @author Gui
 *
 */

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public void salvar (Cliente cliente) {
		cliente.setAtivo(true);
		this.repository.save(cliente);
	}
	
	public void deletar(Cliente cliente) {
		cliente.setAtivo(false);
		this.repository.save(cliente);
	}
	
	public void deletar(Long id)
	{
		this.repository.deleteById(id);
	}
	
	public List<Cliente> buscarTodos()
	{
		return this.repository.findByAtivoTrue();
	}
	
	public Cliente buscar(Long id)
	{
		return this.repository.getOne(id);
	}
}