/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
import com.projeto.estacionai.service.FuncionarioService;
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
	private VeiculoService serviceVeiculo;
	
	@Autowired
	private TicketService service;
	
	@Autowired
	private TicketSujeito sujeito;

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
		mv.addObject("placa", "AAA-1111");
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
	
	@RequestMapping(value="/processar", method=RequestMethod.POST, params={"gerar=Gerar"})
	public ModelAndView gerarTicket(@RequestParam("placa") String placa, RedirectAttributes attributes)
	{
		
		//salvamento do ticket novo
		Veiculo veiculo = this.serviceVeiculo.buscarPorPlaca(placa);
		
		if(veiculo == null)
		{
			attributes.addFlashAttribute("erro", "Veiculo não encontrado. Tente novamente!");
			return new ModelAndView("redirect:/home");
		}
		
		String codigo = "";
		try {
			codigo = DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(placa.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Ticket ticket = new Ticket();
		ticket.setPlaca(placa);
		ticket.setCodigo(codigo);
		ticket.setAtivo(true);
		ticket.setHorarioChegada(LocalDateTime.now());
		ticket.setHorarioSaida(LocalDateTime.now());
		ticket.setTotal(this.service.calcularTotal(ticket));
		ticket.setCliente(veiculo.getCliente());
		this.service.gerarTicket(ticket);
		
		//desanexando os que já tem
		this.sujeito.getObservadores().clear();
		//alertando os observadores 
		this.sujeito.anexar(new EntradaSaidaObserver(sujeito));
		this.sujeito.anexar(new ClienteMovimentoObserver(sujeito));
		this.sujeito.setarEstado(this.service.buscarUltimo());
		
		attributes.addFlashAttribute("sucesso", "Ticket gerado com sucesso! Clique aqui para ver.");
		ModelAndView mv = new ModelAndView("redirect:/home");
		return mv;
	}
	


}
