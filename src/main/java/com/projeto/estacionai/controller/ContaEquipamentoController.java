/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.estacionai.model.ContaEquipamento;
import com.projeto.estacionai.model.ContaReceber;
import com.projeto.estacionai.repository.ContaEquipamentoRepositorySearch;
import com.projeto.estacionai.service.ContaEquipamentoService;

/**
 *
 * @author Alisson
 */
@Controller
@RequestMapping("/contas/equipamento")
public class ContaEquipamentoController {
	
	
	@Autowired
	private ContaEquipamentoService service;
	
	@Autowired
	private ContaEquipamentoRepositorySearch search;
	
	@GetMapping
	public ModelAndView index(ContaEquipamento filtro)
	{		
		ModelAndView mv = new ModelAndView("contas/equipamento/v-conta-equipamento");
		filtro.setAtivo(true);
		mv.addObject("contas", this.search.filtrar(filtro));
		mv.addObject("filtro", filtro);
		return mv;
	}
	
	@PostMapping
	public ModelAndView listarEspecifico(ContaEquipamento filtro)
	{		
		return index(filtro);
	}
	
	
	@GetMapping("/novo")
	public ModelAndView novo(ContaEquipamento contaEquipamento)
	{
		ModelAndView mv = new ModelAndView("contas/equipamento/v-cadastro-conta");
		mv.addObject("contaEquipamento", contaEquipamento);
		return mv;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editarVeiculo(@PathVariable Long id)
	{
		return novo(service.buscar(id));
	}
	
	
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id, RedirectAttributes attributes)
	{
		this.service.deletar(this.service.buscar(id));
		attributes.addFlashAttribute("mensagem", "Conta removida com sucesso!");
		return "redirect:/contas/equipamento";
	}
	
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid ContaEquipamento contaEquipamento, BindingResult result, RedirectAttributes redirectAttributes)
	{
				
		
		if(result.hasErrors())
		{
			return novo(contaEquipamento);
		}
		
		
		if(contaEquipamento.getId() == null)
		{
			
			this.service.salvar(contaEquipamento);
			redirectAttributes.addFlashAttribute("mensagem", "A conta foi cadastrada com sucesso!");
			return new ModelAndView("redirect:/contas/equipamento/novo");
		}
		else
		{
			this.service.salvar(contaEquipamento);
			redirectAttributes.addFlashAttribute("mensagem", "A conta foi atualizada com sucesso!");
			return new ModelAndView("redirect:/contas/equipamento/editar/" + contaEquipamento.getId());
		}
		
	}
	

}
