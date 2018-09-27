package com.projeto.estacionai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.estacionai.model.Funcionario;
import com.projeto.estacionai.repository.FuncionarioRepositorySearch;
import com.projeto.estacionai.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
		
		@Autowired
		private FuncionarioService service;
		@Autowired
		private FuncionarioRepositorySearch search;
		
		@GetMapping
		public ModelAndView listar(Funcionario filtro)
		{
			
			ModelAndView mv = new ModelAndView("funcionarios/v-lista-funcionario");
//			if((filtro.getCpf() == null || filtro.getCpf().trim().equals("")) && 
//				(filtro.getPis() == null || filtro.getPis().trim().equals("")) && 
//				(filtro.getNome() == null || filtro.getNome().trim().equals("")) && 
//				(filtro.getFuncao() == null || filtro.getFuncao().trim().equals("")) && 
//				(filtro.getTelefone() == null || filtro.getTelefone().trim().equals("")) &&
//				(filtro.getNivelPermissao() == null || filtro.getNivelPermissao() < 0))
//			{
//				mv.addObject("funcionarios", service.buscarTodos());
//				mv.addObject("filtro", new Funcionario());
//			}
//			else
//			{
//				
//				mv.addObject("funcionario", search.filtrar(filtro));
//				mv.addObject("filtro", filtro);
//			}
			filtro.setAtivo(true);
			mv.addObject("funcionarios", search.filtrar(filtro));
			mv.addObject("filtro", filtro);
			mv.addObject("username", SecurityContextHolder.getContext().getAuthentication().getName());
			return mv;
		}
		
		@PostMapping
		public ModelAndView listarEspecifico(Funcionario filtro)
		{
			return listar(filtro);
		}
		
		@GetMapping("/user")
		public ModelAndView meuPerfil()
		{
			String login = SecurityContextHolder.getContext().getAuthentication().getName();
			Funcionario user = service.buscarUser(login);
			ModelAndView mv = new ModelAndView("funcionarios/v-perfil-funcionario");
			mv.addObject("username", login);
			mv.addObject("user", user);
			return mv;
		}
		
		@GetMapping("/novo")
		public ModelAndView novo(Funcionario funcionario)
		{
			ModelAndView mv = new ModelAndView("funcionarios/v-cadastro-funcionario");
			mv.addObject(funcionario);			
			return mv;
		}
		
		@GetMapping("/editar/{id}")
		public ModelAndView editar(@PathVariable Long id)
		{
			return novo(service.buscar(id));
		}
		
		@DeleteMapping("/{id}")
		public String deletar(@PathVariable Long id, RedirectAttributes attributes)
		{
			this.service.deletar(this.service.buscar(id));
			
			attributes.addFlashAttribute("mensagem", "Funcionario removido com sucesso!");
			
			return "redirect:/funcionarios";
		}
		
		@PostMapping("/novo")
		public ModelAndView salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes)
		{
			if(result.hasErrors())
			{
				return novo(funcionario);
			}
			
			
				
			if(funcionario.getId() == null)
			{
				
				service.salvar(funcionario);
				
				attributes.addFlashAttribute("mensagem", "Funcionario cadastrado com sucesso!");
				
				return new ModelAndView("redirect:/funcionarios/novo");
			}
			else
			{
				
				service.salvar(funcionario);
				
				attributes.addFlashAttribute("mensagem", "Funcionario atualizado com sucesso!");
				
				return new ModelAndView("redirect:/funcionarios/editar/" + funcionario.getId());
			}
		}


	}