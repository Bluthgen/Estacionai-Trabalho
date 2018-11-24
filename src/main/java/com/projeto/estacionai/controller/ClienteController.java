package com.projeto.estacionai.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.apache.tomcat.jni.Local;
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

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.projeto.estacionai.model.Cliente;
import com.projeto.estacionai.model.Mensalidade;
import com.projeto.estacionai.model.Veiculo;
import com.projeto.estacionai.repository.ClienteRepositorySearch;
import com.projeto.estacionai.repository.VeiculoRepositorySearch;
import com.projeto.estacionai.service.ClienteService;
import com.projeto.estacionai.service.MensalidadeService;
import com.projeto.estacionai.service.VeiculoService;

import antlr.collections.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
		
		@Autowired
		private ClienteService service;
		@Autowired
		private ClienteRepositorySearch search;
		@Autowired
		private VeiculoService serviceVeiculo;
		@Autowired
		private VeiculoRepositorySearch searchVeiculo;
		@Autowired
		private MensalidadeService serviceMensalidade;
		
		@GetMapping
		public ModelAndView listar(Cliente filtro)
		{
			
			ModelAndView mv = new ModelAndView("clientes/v-lista-cliente");
			filtro.setAtivo(true);
			mv.addObject("clientes", search.filtrar(filtro));
			mv.addObject("filtro", filtro);
			return mv;
		}
		
		@PostMapping
		public ModelAndView listarEspecifico(Cliente filtro)
		{
			return listar(filtro);
		}
		
		
		
		@GetMapping("/novo")
		public ModelAndView novo(Cliente cliente)
		{
			ModelAndView mv = new ModelAndView("clientes/v-cadastro-cliente");
			mv.addObject("cliente", cliente);			
			return mv;
		}
		
		@GetMapping("/editar/{id}")
		public ModelAndView editar(@PathVariable Long id)
		{
			return novo(service.buscar(id));
		}
		
		//metodo para fazer a busca de veiculos
		@PostMapping("/veiculos/{id}")
		public ModelAndView listarEspecificoVeiculo(@PathVariable Long id, Veiculo filtro)
		{
			Cliente cliente = service.buscar(id);
			filtro.setCliente(cliente);
			filtro.setAtivo(true);
			
			ModelAndView mv = new ModelAndView("clientes/v-lista-veiculo");
			mv.addObject("filtro", filtro);
			mv.addObject("veiculos", searchVeiculo.filtrar(filtro));
			mv.addObject("cliente", cliente);
			return mv;
		}
		
		@GetMapping("/veiculos/{id}")
		public ModelAndView veiculos(@PathVariable Long id, Veiculo filtro)
		{
//			Veiculo veiculo = new Veiculo();
			Cliente cliente = service.buscar(id);
			filtro.setCliente(cliente);
			filtro.setAtivo(true);
			
			ModelAndView mv = new ModelAndView("clientes/v-lista-veiculo");
			mv.addObject("filtro", filtro);
			mv.addObject("veiculos", searchVeiculo.filtrar(filtro));
			mv.addObject("cliente", cliente);
			return mv;
		}

		
		@GetMapping("/veiculos/{idCliente}/novo")
		public ModelAndView salvarVeiculo(Veiculo veiculo, @PathVariable Long idCliente)
		{
			//return novoVeiculo(serviceVeiculo.buscar(id));
			ModelAndView mv = new ModelAndView("clientes/v-cadastro-veiculo");
			mv.addObject("clienteAtual", service.buscar(idCliente));
			mv.addObject("veiculo", veiculo);	
			return mv;
		}
		
		@GetMapping("/veiculos/{idCliente}/editar/{id}")
		public ModelAndView editarVeiculo(@PathVariable Long idCliente, @PathVariable Long id)
		{
			ModelAndView mv = new ModelAndView("clientes/v-cadastro-veiculo");
			mv.addObject("clienteAtual", service.buscar(idCliente));
			mv.addObject("veiculo", serviceVeiculo.buscar(id));			
			return mv;
		}
		
		public ModelAndView novoVeiculo(Cliente cliente)
		{
			ModelAndView mv = new ModelAndView("clientes/v-cadastro-veiculo");
//			mv.addObject("clienteAtual", cliente);
			mv.addObject("veiculo", new Veiculo());			
			return mv;
		}
		
		@GetMapping("/editar/veiculo/novo")
		public ModelAndView novoVeiculo(Veiculo veiculo)
		{
			ModelAndView mv = new ModelAndView("clientes/v-cadastro-veiculo");
			mv.addObject(veiculo);			
			return mv;
		}
		
		
		@PostMapping("/veiculos/{idCliente}/novo")
		public ModelAndView salvarVeiculo(@Valid Veiculo veiculo, BindingResult result, RedirectAttributes attributes, @PathVariable Long idCliente)
		{
			veiculo.setCliente(service.buscar(idCliente));
			if(result.hasErrors())
			{
				
				return salvarVeiculo(veiculo, idCliente);
			}
			
			
			if(veiculo.getId() == null)
			{
				serviceVeiculo.salvar(veiculo);
	
				attributes.addFlashAttribute("mensagem", "Veiculo cadastrado com sucesso!");
					
				return new ModelAndView("redirect:/clientes/veiculos/" + idCliente + "/novo" );
			}
			else
			{
				serviceVeiculo.salvar(veiculo);

				attributes.addFlashAttribute("mensagem", "Veiculo atualizado com sucesso!");
					
				return new ModelAndView("redirect:/clientes/veiculos/" + idCliente + "/editar/" + veiculo.getId() );
			}
			
		}
		
		@DeleteMapping("/{id}")
		public String deletar(@PathVariable Long id, RedirectAttributes attributes)
		{
			
			this.service.deletar(this.service.buscar(id));
			
			attributes.addFlashAttribute("mensagem", "Cliente removido com sucesso!");
			
			return "redirect:/clientes";
		}
		
		@DeleteMapping("/veiculos/{id}")
		public String deletarVeiculo(@PathVariable Long id, RedirectAttributes attributes)
		{
			Veiculo veiculo = this.serviceVeiculo.buscar(id);
			Long idCliente = veiculo.getCliente().getId();
			this.serviceVeiculo.deletar(veiculo);
			
			attributes.addFlashAttribute("mensagem", "Veiculo removido com sucesso!");
			
			return "redirect:/clientes/veiculos/" + idCliente;
		}
		
		@PostMapping("/novo")
		public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes)
		{	
			if(result.hasErrors())
			{
				return novo(cliente);
			}
			
			if(cliente.getId() == null)
			{
				// Caso as vagas passadas para carro sejam maiores que as vagas totais, as vagas do carro serão as totais
				if(cliente.getNumeroVagasCarro() > cliente.getNumeroVagas()) {
					cliente.setNumeroVagasCarro(cliente.getNumeroVagas());
				}
				
				service.salvar(cliente);
				
				Cliente cadastrado = null;
				java.util.List<Cliente> clientes = search.filtrar(cliente);
				
				
				cadastrado = clientes.get(0);
				
				
				vincularMensalidades(cadastrado);
				
				attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
				
				return new ModelAndView("redirect:/clientes/novo");
			}
			else
			{
				
				service.salvar(cliente);
				
				attributes.addFlashAttribute("mensagem", "Cliente atualizado com sucesso!");
				
				
				
				return new ModelAndView("redirect:/clientes/editar/" + cliente.getId());
			}
		}
		
		
		
		public void vincularMensalidades(Cliente cliente)
		{
			
			
			Integer valorVagaCarro = 45;
			Integer valorVagaMoto = 30;
			Integer qtdVagasCarro = cliente.getNumeroVagasCarro();
			Integer qtdVagasMoto = cliente.getNumeroVagas() - qtdVagasCarro;
	
			Integer valorPorMensalidade = (valorVagaCarro * qtdVagasCarro + valorVagaMoto * qtdVagasMoto);
			
			LocalDate dataPagamento = LocalDate.now();
			Integer diaPagamento = cliente.getDiaPagamento();
			dataPagamento = dataPagamento.withDayOfMonth(diaPagamento);
	
			
			for(int i = 1; i <= 12; i++)
			{
				Mensalidade mensalidade = new Mensalidade();
				mensalidade.setDataVencimento(dataPagamento);
				mensalidade.setAtivo(true);
				mensalidade.setCliente(cliente);
				if(i == 1 && LocalDate.now().getDayOfMonth() > diaPagamento) {
					mensalidade.setStatus("ATRASO");
				}else {
					mensalidade.setStatus("PENDENTE");
				}
				mensalidade.setValor(valorPorMensalidade.doubleValue());
				serviceMensalidade.salvar(mensalidade);
				dataPagamento = dataPagamento.plusMonths(1);
			}
			
		}


	}
