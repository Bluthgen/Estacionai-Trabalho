package com.projeto.estacionai.controller;

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

import com.projeto.estacionai.model.Mensalidade;
import com.projeto.estacionai.repository.MensalidadeRepositorySearch;
import com.projeto.estacionai.service.MensalidadeService;

@Controller
@RequestMapping("/mensalidades")
public class MensalidadeController {
	
	@Autowired
	private MensalidadeService service;
	
	@Autowired
	private MensalidadeRepositorySearch search;
	
	@GetMapping
	public ModelAndView listar(Mensalidade filtro)
	{
		
		ModelAndView mv = new ModelAndView("mensalidade/v-lista-mensalidade");
		filtro.setAtivo(true);
		mv.addObject("mensalidades", search.filtrar(filtro));
		mv.addObject("filtro", filtro);
		return mv;
	}
	
	@PostMapping
	public ModelAndView listarEspecifico(Mensalidade filtro)
	{
		return listar(filtro);
	}
	
}
