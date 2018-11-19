package com.projeto.estacionai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.estacionai.model.Cliente;
import com.projeto.estacionai.model.Funcionario;
import com.projeto.estacionai.model.Mensalidade;
import com.projeto.estacionai.repository.ClienteRepositorySearch;
import com.projeto.estacionai.repository.MensalidadeRepositorySearch;
import com.projeto.estacionai.service.MensalidadeService;
import com.projeto.estacionai.util.Email;

@Controller
@RequestMapping("/mensalidades")
public class MensalidadeController {
	
	@Autowired
	private Email email;
	
	@Autowired
	private MensalidadeService service;
	
	@Autowired
	private MensalidadeRepositorySearch search;
	
	@Autowired
	private ClienteRepositorySearch searchCliente;
	
	@GetMapping
	public ModelAndView listar(Mensalidade filtro)
	{
		
		ModelAndView mv = new ModelAndView("mensalidades/v-lista-mensalidade");
		filtro.setAtivo(true);
		mv.addObject("mensalidades", search.filtrar(filtro));
		mv.addObject("filtro", filtro);
		return mv;
	}
	
	
	@RequestMapping(value="/processar", method=RequestMethod.POST, params={"pagamento=Pagamento"})
	public ModelAndView confirmarPagamento(@Valid Mensalidade mensalidade, BindingResult result, RedirectAttributes attributes)
	{
		System.out.println("Cliente " + mensalidade.getCliente().getId());
		mensalidade.setStatus("PAGO");
		this.service.salvar(mensalidade);
		
		attributes.addFlashAttribute("mensagem", "Pagamento confirmado com sucesso!");
		
		return new ModelAndView("redirect:/mensalidades/editar/" + mensalidade.getId());
	}
	
	@RequestMapping(value="/processar", method=RequestMethod.POST, params={"enviar=Email"})
	public ModelAndView enviarEmail(@Valid Mensalidade mensalidade, BindingResult result, RedirectAttributes attributes)
	{
		
		if(this.email == null)
			this.email = new Email();
		
		
		Double valor = mensalidade.getValor();
		
		String email = mensalidade.getCliente().getEmail();
		this.email.enviarMensagem(email, "Cobrança Estacionaí", "Boleto no valor de:R$ " + valor);
		
		attributes.addFlashAttribute("mensagem", "Email enviado com sucesso!");
		
		return new ModelAndView("redirect:/mensalidades/editar/" + mensalidade.getId());
		
	}
	
	
	@PostMapping
	public ModelAndView listarEspecifico(Mensalidade filtro)
	{
		Cliente cli = new Cliente();
		if(filtro.getCliente() != null && !filtro.getCliente().getNome().equals(""))
		{
			List<Cliente> clientes = searchCliente.filtrar(filtro.getCliente());
			
			if(clientes.size() > 0)
			{
				cli = clientes.get(0);
			}
		}
		
		filtro.setCliente(cli);
		
		return listar(filtro);
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id)
	{
		ModelAndView mv = new ModelAndView("mensalidades/v-editar-mensalidade");
		mv.addObject(service.buscar(id));			
		return mv;
	}
	
}
