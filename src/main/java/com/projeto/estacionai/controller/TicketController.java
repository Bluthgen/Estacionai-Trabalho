package com.projeto.estacionai.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.estacionai.model.Cliente;
import com.projeto.estacionai.model.Ticket;
import com.projeto.estacionai.model.Veiculo;
import com.projeto.estacionai.repository.VeiculoRepositorySearch;
import com.projeto.estacionai.service.ClienteService;
import com.projeto.estacionai.service.FuncionarioService;
import com.projeto.estacionai.service.TicketService;
import com.projeto.estacionai.service.VeiculoService;

/**
*
* @author Eduardo
*/
@Controller
@RequestMapping("/tickets")
public class TicketController {
	@Autowired
	private TicketService service;
	
	@Autowired
	private VeiculoService vService;
	
	@Autowired
	private ClienteService cService;
	
	@Autowired
	private FuncionarioService serviceFunc;
	
	@GetMapping("/gerar/{id}")
	public ModelAndView gerar(@PathVariable Long id)
	{		
		ModelAndView mv = new ModelAndView("ticket/v-gerar-ticket");
		mv.addObject("ticket", this.service.buscarTicket(id));
		return mv;
	}
	
	@PostMapping("/gerar")
	public ModelAndView gerarTicket(@RequestParam("placa") String placa, RedirectAttributes attributes)
	{
		return null;
	}
	
	
}
