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

import com.projeto.estacionai.model.Cliente;
import com.projeto.estacionai.model.Veiculo;
import com.projeto.estacionai.repository.VeiculoRepositorySearch;
import com.projeto.estacionai.service.ClienteService;
import com.projeto.estacionai.service.VeiculoService;

/**
 * 
 * @author ALISSON 
 *
 */

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {
	
	@Autowired
	private VeiculoService service;
	@Autowired
	private VeiculoRepositorySearch search;
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ModelAndView listar(Veiculo filtro)
	{
		
		ModelAndView mv = new ModelAndView("veiculos/v-lista-veiculo");
//		if((filtro.getPlaca() == null || filtro.getPlaca().trim().equals("")) && 
//			(filtro.getRenavam() == null || filtro.getRenavam().trim().equals("")) && 
//			(filtro.getModelo() == null || filtro.getModelo().trim().equals("")) && 
//			(filtro.getAno() == null || filtro.getAno() < 1500) && 
//			(filtro.getTipo() == null || filtro.getTipo().trim().equals("")))
//		{
//			mv.addObject("veiculos", service.buscarTodos());
//			mv.addObject("filtro", new Veiculo());
//		}
//		else
//		{
			filtro.setAtivo(true);
			mv.addObject("veiculos", search.filtrar(filtro));
			mv.addObject("filtro", filtro);
//		}
		return mv;
	}
	
	
	@PostMapping
	public ModelAndView listarEspecifico(Veiculo filtro)
	{
		return listar(filtro);
	}
	
	@GetMapping("/visualizar/{id}")
	public ModelAndView visualizar(@PathVariable Long id)
	{
		Veiculo filtro = new Veiculo();
		Cliente cliente = clienteService.buscar(id);
		filtro.setCliente(cliente);
		
		ModelAndView mv = new ModelAndView("veiculos/v-lista-veiculo");
		mv.addObject("veiculos", search.filtrar(filtro));
		mv.addObject("cliente", cliente);
		return mv;
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Veiculo veiculo)
	{
		ModelAndView mv = new ModelAndView("veiculos/v-cadastro-veiculo");
		mv.addObject(veiculo);
		return mv;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id)
	{
		return novo(service.buscar(id));
	}
	
	@GetMapping("/editar/{id}/{idCliente}")
	public ModelAndView editarComCliente(@PathVariable Long id, @PathVariable Long idCliente)
	{
		ModelAndView mv = new ModelAndView("veiculos/v-cadastro-veiculo");
		mv.addObject("veiculo", service.buscar(id));
		mv.addObject("cliente", clienteService.buscar(idCliente));
		return mv;
	}
	
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id, RedirectAttributes attributes)
	{
		this.service.deletar(this.service.buscar(id));
		
		attributes.addFlashAttribute("mensagem", "Veiculo removido com sucesso!");
		
		return "redirect:/veiculos";
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Veiculo veiculo, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(veiculo);
		}
		
		
			
		if(veiculo.getId() == null)
		{
			
			service.salvar(veiculo);
			
			attributes.addFlashAttribute("mensagem", "Veiculo cadastrado com sucesso!");
			
			return new ModelAndView("redirect:/veiculos/novo");
		}
		else
		{
			
			service.salvar(veiculo);
			
			attributes.addFlashAttribute("mensagem", "Veiculo atualizado com sucesso!");
			
			return new ModelAndView("redirect:/veiculos/editar/" + veiculo.getId());
		}
	}


}
