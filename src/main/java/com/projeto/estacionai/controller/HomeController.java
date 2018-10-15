/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.projeto.estacionai.observer.EntradaSaidaObserver;
import com.projeto.estacionai.observer.TicketSujeito;
import com.projeto.estacionai.service.FuncionarioService;
import com.projeto.estacionai.service.VagaService;

/**
 *
 * @author Alisson, Guilherme
 */
@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private VagaService serviceVaga;
	

	@Autowired
	private FuncionarioService serviceFunc;
	
	@GetMapping
	public ModelAndView index()
	{		
		ModelAndView mv = new ModelAndView("home/v-home");
		mv.addObject("user", serviceFunc.buscarUser(
				SecurityContextHolder.getContext().getAuthentication().getName()));
		mv.addObject("countMoto", this.serviceVaga.buscarPorTipo(1));
		mv.addObject("countCarro", this.serviceVaga.buscarPorTipo(2));
		mv.addObject("countDeficiente", this.serviceVaga.buscarPorTipo(3));
		mv.addObject("countMotoOcup", this.serviceVaga.buscarPorTipoOcupadas(1));
		mv.addObject("countCarroOcup", this.serviceVaga.buscarPorTipoOcupadas(2));
		mv.addObject("countDeficienteOcup", this.serviceVaga.buscarPorTipoOcupadas(3));
		return mv;
	}
	
	
	@GetMapping("/observer")
	public ModelAndView iniciarObservadores()
	{
		
		TicketSujeito sujeito = new TicketSujeito();
		sujeito.anexar(new EntradaSaidaObserver(sujeito));	
		
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
	

}
