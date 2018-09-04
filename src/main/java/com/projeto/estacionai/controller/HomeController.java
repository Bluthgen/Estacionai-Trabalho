/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.estacionai.observer.EntradaSaidaObserver;
import com.projeto.estacionai.observer.TicketSujeito;

/**
 *
 * @author Alisson
 */
@Controller
@RequestMapping("/home")
public class HomeController {
	
	
	@GetMapping
	public ModelAndView index()
	{		
		ModelAndView mv = new ModelAndView("home/v-home");
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
