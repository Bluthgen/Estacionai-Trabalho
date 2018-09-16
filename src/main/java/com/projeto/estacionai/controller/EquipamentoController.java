package com.projeto.estacionai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
		ModelAndView mv = new ModelAndView("equipamentos/v-editar-equipamento");
		mv.addObject("equipamento", service.buscar(id));
		return mv;
	}
	
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id, RedirectAttributes attributes)
	{
		service.deletar(id);
		
		attributes.addFlashAttribute("mensagem", "Equipamento removido com sucesso!");
		
		return "redirect:/equipamentos";
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Equipamento equipamento, BindingResult result, RedirectAttributes redirectAttributes)
	{
				
		
		if(result.hasErrors())
		{
			return novo(equipamento);
		}
		
		
		if(equipamento.getId() == null)
		{
			
			this.service.salvar(equipamento);
			redirectAttributes.addFlashAttribute("mensagem", "A conta foi cadastrada com sucesso!");
			return new ModelAndView("redirect:/contas/pagar/novo");
		}
		else
		{
			this.service.salvar(equipamento);
			redirectAttributes.addFlashAttribute("mensagem", "A conta foi atualizada com sucesso!");
			return new ModelAndView("redirect:/contas/pagar/editar/" + equipamento.getId());
		}
		
	}
}
