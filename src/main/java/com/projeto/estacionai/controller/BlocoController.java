/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.controller;

import com.projeto.estacionai.model.Bloco;
import com.projeto.estacionai.model.Vaga;
import com.projeto.estacionai.repository.BlocoRepositorySearch;
import com.projeto.estacionai.service.BlocoService;
import com.projeto.estacionai.service.VagaService;
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

/**
 *
 * @author Eduardo
 */
@Controller
@RequestMapping("/blocos")
public class BlocoController {
	
	@Autowired
	private BlocoService service;
	@Autowired
	private BlocoRepositorySearch search;
    @Autowired
    private VagaService vagaService;
    
	
	@GetMapping
	public ModelAndView listar(Bloco filtro)
	{
		
		ModelAndView mv = new ModelAndView("blocos/v-lista-bloco");
		System.out.println("valores: "  + filtro.getNome() + "," + filtro.getNumVagas() + "," + filtro.getMaxVagas() + "," + filtro.getId());
//		if((filtro.getNumVagas() == null || filtro.getNumVagas() > 1000) && 
//			(filtro.getMaxVagas() == null || filtro.getMaxVagas() > 1000) &&
//			(filtro.getId() == null || filtro.getId() < 1))
//		if((filtro.getNumVagas() == null || filtro.getNumVagas() > 1000) && 
//				(filtro.getMaxVagas() == null || filtro.getMaxVagas() > 1000) &&
//				(filtro.getId() == null || filtro.getId() < 1))
//		{
//			mv.addObject("blocos", service.buscarTodos());
//			mv.addObject("filtro", new Bloco());
//		}
//		else
//		{
//			mv.addObject("blocos", search.filtrar(filtro));
//			mv.addObject("filtro", filtro);
//		}
		
//		if(filtro != null)
//		{
//			
			filtro.setAtivo(true);
			mv.addObject("blocos", search.filtrar(filtro));
			mv.addObject("filtro", filtro);
			return mv;
//		}
//		
//		mv.addObject("blocos", service.buscarTodos());
//		mv.addObject("filtro", new Bloco());
//		return mv;
		
	}
	
	@PostMapping
	public ModelAndView listarEspecifico(Bloco filtro)
	{
		return listar(filtro);
	}
	
	
	
	@GetMapping("/novo")
	public ModelAndView novo(Bloco bloco)
	{
		ModelAndView mv = new ModelAndView("blocos/v-cadastro-bloco");
		mv.addObject(bloco);
//		mv.addObject("vagas", vagaService.buscarTodosDesocupadas());
		return mv;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id)
	{
		ModelAndView mv = new ModelAndView("blocos/v-editar-bloco");
		mv.addObject("bloco", service.buscar(id));
		mv.addObject("vagas", vagaService.buscarTodosDesocupadas());
		return mv;
//		return novo(service.buscar(id));
	}
	
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id, RedirectAttributes attributes)
	{
		service.deletar(id);
		
		attributes.addFlashAttribute("mensagem", "Bloco removido com sucesso!");
		
		return "redirect:/blocos";
	}
	
	@DeleteMapping("/vaga/{id}")
	public String deletarVaga(@PathVariable Long id, RedirectAttributes attributes)
	{
		
		Long idBloco = vagaService.buscar(id).getBloco().getId();
		service.buscar(idBloco).setNumVagas(service.buscar(idBloco).getNumVagas() - 1);
		
		vagaService.deletar(id);
		
		attributes.addFlashAttribute("mensagem", "Vaga removida com sucesso!");
		
		return "redirect:/blocos/editar/" + idBloco;
	}
	
	@GetMapping("/vaga/novo/{bloco}/{tipo}")
	public ModelAndView salvarVaga(@PathVariable Integer tipo, @PathVariable Long bloco)
	{
		
		//adicionando nova vaga
		Vaga vaga = new Vaga();
		vaga.setTipo(tipo);
		vaga.setOcupada(false);
		vaga.setBloco(service.buscar(bloco));
		
		vagaService.salvar(vaga);
		service.buscar(bloco).setNumVagas(service.buscar(bloco).getNumVagas() + 1);
			
		return new ModelAndView("redirect:/blocos/editar/" + bloco);
		
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarBloco(@Valid Bloco bloco, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(bloco);
		}
		
		service.salvar(bloco);
		Bloco ultimo = service.buscarUltimo();
		attributes.addFlashAttribute("mensagem", "Bloco cadastrado com sucesso!");
		
		return new ModelAndView("redirect:/blocos/editar/" + ultimo.getId());
		
	}
	
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Bloco bloco, BindingResult result, RedirectAttributes attributes)
	{
		//bloco.setNumVagas(bloco.getVagas().size());
		
		if(result.hasErrors())
		{
			return novo(bloco);
		}
		
			
		if(bloco.getId() == null)
		{
			
			service.salvar(bloco);
//			Bloco ultimo = service.buscarUltimo();
//			for (Vaga vaga : bloco.getVagas()) {
//				vaga.setOcupada(true);
//				vaga.setBloco(ultimo);
//				vagaService.salvar(vaga);
//			}
			
			attributes.addFlashAttribute("mensagem", "Bloco cadastrado com sucesso!");
			
			return new ModelAndView("redirect:/blocos/novo");
		}
		else
		{
			
			service.salvar(bloco);
			
//			for (Vaga vaga : bloco.getVagas()) {
//				vaga.setOcupada(true);
//				vaga.setBloco(bloco);
//				vagaService.salvar(vaga);
//			}
			
			attributes.addFlashAttribute("mensagem", "Bloco atualizado com sucesso!");
			
			return new ModelAndView("redirect:/blocos/editar/" + bloco.getId());
		}
	}


}
