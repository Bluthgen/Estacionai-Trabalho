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

import com.projeto.estacionai.model.Equipamento;
import com.projeto.estacionai.repository.EquipamentoRepositorySearch;
import com.projeto.estacionai.service.EquipamentoService;

@Controller
@RequestMapping("/equipamentos")
public class EquipamentoController {
	
	@Autowired
	private EquipamentoService service;
	
	@Autowired
	private EquipamentoRepositorySearch search;
	
	@GetMapping
	public ModelAndView listar(Equipamento filtro)
	{
		
		ModelAndView mv = new ModelAndView("equipamentos/v-lista-equipamento");
		filtro.setAtivo(true);
		mv.addObject("equipamentos", search.filtrar(filtro));
		mv.addObject("filtro", filtro);
		return mv;
	}
	
	@PostMapping
	public ModelAndView listarEspecifico(Equipamento filtro)
	{
		return listar(filtro);
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Equipamento equipamento)
	{
		ModelAndView mv = new ModelAndView("equipamentos/v-cadastro-equipamento");
		mv.addObject(equipamento);			
		return mv;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id)
	{
		return novo(service.buscar(id));
	}
	
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id, RedirectAttributes attributes)
	{
		service.deletar(service.buscar(id));
		
		attributes.addFlashAttribute("mensagem", "Equipamento removido com sucesso!");
		
		return "redirect:/equipamentos";
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Equipamento equipamento, BindingResult result, RedirectAttributes redirectAttributes)
	{
				
		System.out.println(equipamento.getId());
		if(result.hasErrors())
		{
			return novo(equipamento);
		}
		
		
		if(equipamento.getId() == null)
		{
			
			this.service.salvar(equipamento);
			redirectAttributes.addFlashAttribute("mensagem", "O equipamento foi cadastrado com sucesso!");
			return new ModelAndView("redirect:/equipamentos/novo");
		}
		else
		{
			this.service.salvar(equipamento);
			redirectAttributes.addFlashAttribute("mensagem", "O equipamento foi atualizado com sucesso!");
			return new ModelAndView("redirect:/equipamentos/editar/" + equipamento.getId());
		}
		
	}
}
