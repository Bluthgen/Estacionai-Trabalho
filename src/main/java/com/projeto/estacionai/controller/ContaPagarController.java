/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.projeto.estacionai.repository.ContaPagarRepositorySearch;
import com.projeto.estacionai.service.ContaPagarService;
import com.projeto.estacionai.service.FuncionarioService;

/**
 *
 * @author Alisson
 */
@Controller
@RequestMapping("/contas/pagar")
public class ContaPagarController {
	
	
	@Autowired
	private ContaPagarService service;
	
	@Autowired
	private ContaPagarRepositorySearch search;
	
	@Autowired
	private FuncionarioService serviceFunc;
	
	@GetMapping
	public ModelAndView index(ContaPagar filtro)
	{		
		ModelAndView mv = new ModelAndView("contas/pagar/v-conta-pagar");
		filtro.setAtivo(true);
		mv.addObject("contas", this.search.filtrar(filtro));
		mv.addObject("filtro", filtro);
		mv.addObject("user", serviceFunc.buscarUser(
				SecurityContextHolder.getContext().getAuthentication().getName()));
		return mv;
	}
	
	@PostMapping
	public ModelAndView listarEspecifico(ContaPagar filtro)
	{		
		return index(filtro);
	}
	
	
	@GetMapping("/novo")
	public ModelAndView novo(ContaPagar contaPagar)
	{
		ModelAndView mv = new ModelAndView("contas/pagar/v-cadastro-conta");
		mv.addObject("contaPagar", contaPagar);
		mv.addObject("user", serviceFunc.buscarUser(
				SecurityContextHolder.getContext().getAuthentication().getName()));
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
		

		return "redirect:/contas/pagar";
	}
	
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid ContaPagar contaPagar, BindingResult result, RedirectAttributes redirectAttributes)
	{
				
		
		if(result.hasErrors())
		{
			return novo(contaPagar);
		}
		
		
		if(contaPagar.getId() == null)
		{
			
			this.service.salvar(contaPagar);
			redirectAttributes.addFlashAttribute("mensagem", "A conta foi cadastrada com sucesso!");
			return new ModelAndView("redirect:/contas/pagar/novo");
		}
		else
		{
			this.service.salvar(contaPagar);
			redirectAttributes.addFlashAttribute("mensagem", "A conta foi atualizada com sucesso!");
			return new ModelAndView("redirect:/contas/pagar/editar/" + contaPagar.getId());
		}
		
	}
	

}
