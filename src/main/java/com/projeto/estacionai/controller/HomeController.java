/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.estacionai.model.Ticket;
import com.projeto.estacionai.model.Veiculo;
import com.projeto.estacionai.observer.ClienteMovimentoObserver;
import com.projeto.estacionai.observer.EntradaSaidaObserver;
import com.projeto.estacionai.observer.TicketSujeito;
import com.projeto.estacionai.service.HistoricoEntradaSaidaService;
import com.projeto.estacionai.service.TicketService;
import com.projeto.estacionai.service.VagaService;
import com.projeto.estacionai.service.VeiculoService;

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
	private TicketService service;
	@Autowired
	private VeiculoService serviceVeiculo;
	@Autowired
	private HistoricoEntradaSaidaService serviceHistorico;
	@Autowired
	private TicketSujeito sujeito;
	
	private Authentication authentication;
	private String regra;
	
	@GetMapping
	public ModelAndView index()
	{		
		ModelAndView mv = new ModelAndView("home/v-home");
		mv.addObject("countMoto", this.serviceVaga.buscarPorTipo(1));
		mv.addObject("countCarro", this.serviceVaga.buscarPorTipo(2));
		mv.addObject("countDeficiente", this.serviceVaga.buscarPorTipo(3));
		mv.addObject("countMotoOcup", this.serviceVaga.buscarPorTipoOcupadas(1));
		mv.addObject("countCarroOcup", this.serviceVaga.buscarPorTipoOcupadas(2));
		mv.addObject("countDeficienteOcup", this.serviceVaga.buscarPorTipoOcupadas(3));
		mv.addObject("isGerente", this.verificarGerente());
		return mv;
	}
	
	@GetMapping("/atualizar")
	public String atualizarHistoricoEntradaESaida(Model model)
	{		
		//mv.addObject("historicos", this.serviceHistorico.buscarUltimos5());
		model.addAttribute("historicos", this.serviceHistorico.buscarUltimos5());
		System.out.print("Atualizou a lista");
		return "home/resultadoEntradaESaida :: fragResultadoEntradaESaida";
		
	}
	
	
	@GetMapping("/observer")
	public ModelAndView iniciarObservadores()
	{
		
		TicketSujeito sujeito = new TicketSujeito();
		sujeito.anexar(new EntradaSaidaObserver(sujeito));	
		
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
	
	public Boolean verificarGerente()
	{
		this.authentication = SecurityContextHolder.getContext().getAuthentication();
		authentication.getAuthorities().forEach(a -> regra = a.toString());
		
		if(regra.equals("ROLE_GERENTE"))
		{
			return  true;
		}
		else
		{
			return false;
		}
	}
	
	@RequestMapping(value="/processar", method=RequestMethod.POST, params={"validar=Validar"})
	public ModelAndView validarTicket(@RequestParam("placa") String placa, RedirectAttributes attributes)
	{
		Veiculo veiculo = this.serviceVeiculo.buscarPorPlaca(placa);
		
		if(veiculo == null)
		{
			attributes.addFlashAttribute("erro", "Veiculo não encontrado. Tente novamente!");
			return new ModelAndView("redirect:/home");
		}
		
		Ticket ticket = this.service.buscarTicket(placa);
		
		if(ticket == null)
		{
			attributes.addFlashAttribute("erro", "Não existe ticket para este veiculo. Tente novamente!");
			return new ModelAndView("redirect:/home");
		}
		
		
		ticket.setHorarioSaida(LocalDateTime.now());
		ticket.setTotal(this.service.calcularTotal(ticket));
		this.service.validarTicket(ticket);
		
		if(this.sujeito == null)
			this.sujeito = new TicketSujeito();
		
		//desanexando os que já tem
		this.sujeito.getObservadores().clear();
		//anexando e alertando os observadores
		this.sujeito.anexar(new EntradaSaidaObserver(this.sujeito));
		this.sujeito.anexar(new ClienteMovimentoObserver(this.sujeito));
		this.sujeito.setarEstado(this.service.buscarUltimo());
		
				
		attributes.addFlashAttribute("sucesso", "Ticket validado com sucesso!");
		ModelAndView mv = new ModelAndView("redirect:/home");
		return mv;
	}
	

}
