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

import com.projeto.estacionai.model.ContaPagar;
import com.projeto.estacionai.model.ContaReceber;
import com.projeto.estacionai.repository.ContaReceberRepositorySearch;
import com.projeto.estacionai.service.ContaReceberService;

/**
 *
 * @author Alisson
 */
@Controller
@RequestMapping("/contas/receber")
public class ContaReceberController {
	
	
	@Autowired
	private ContaReceberService service;
	
	@Autowired
	private ContaReceberRepositorySearch search;
	
	@GetMapping
	public ModelAndView index(ContaReceber filtro)
	{		
		ModelAndView mv = new ModelAndView("contas/receber/v-conta-receber");
		filtro.setAtivo(true);
		mv.addObject("contas", this.search.filtrar(filtro));
		mv.addObject("filtro", filtro);
		return mv;
	}
	
	@PostMapping
	public ModelAndView listarEspecifico(ContaReceber filtro)
	{		
		return index(filtro);
	}
	
	
	@GetMapping("/novo")
	public ModelAndView novo(ContaReceber contaReceber)
	{
		ModelAndView mv = new ModelAndView("contas/receber/v-cadastro-conta");
		mv.addObject("contaReceber", contaReceber);
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
		return "redirect:/contas/receber";
	}
	
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid ContaReceber contaReceber, BindingResult result, RedirectAttributes redirectAttributes)
	{			
		
		if(result.hasErrors())
		{
			return novo(contaReceber);
		}
		
		
		if(contaReceber.getId() == null)
		{
			
			this.service.salvar(contaReceber);
			redirectAttributes.addFlashAttribute("mensagem", "A conta foi cadastrada com sucesso!");
			return new ModelAndView("redirect:/contas/receber/novo");
		}
		else
		{
			this.service.salvar(contaReceber);
			redirectAttributes.addFlashAttribute("mensagem", "A conta foi atualizada com sucesso!");
			return new ModelAndView("redirect:/contas/receber/editar/" + contaReceber.getId());
		}
		
	}
	

}
