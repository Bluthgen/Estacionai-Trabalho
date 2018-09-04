package com.projeto.estacionai.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.estacionai.model.Funcionario;
import com.projeto.estacionai.repository.FuncionarioRepositorySearch;


/**
*
* @author Gui
*/
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private FuncionarioRepositorySearch service;
	
	@GetMapping
	public ModelAndView index(Funcionario funcionario)
	{		
		ModelAndView mv = new ModelAndView("login/v-login");
		mv.addObject("funcionario", funcionario);
		return mv;
	}
	
	@PostMapping
	public ModelAndView validar(Funcionario funcionario) {
		return new ModelAndView("redirect:/");
	}
	
	@PostMapping("/logout")
	public ModelAndView logout() {
		return new ModelAndView("redirect:/login");
	}
	
	@GetMapping("/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	
	
	
//	@PostMapping("/logar")
//	public ModelAndView logar(Funcionario funcionario, BindingResult result, RedirectAttributes attributes)
//	{
//		
//		return new ModelAndView("redirect:/sigem/usuarios");
//		
////		funcionario.setNivelPermissao(2); //usuários de nivel gerencial
////		if(result.hasErrors())
////		{
////			return index(funcionario);
////		}
////		
////		
////			if(service.filtrar(funcionario).size() > 0)
////			{
////				
////				return new ModelAndView("redirect:/");
////			}
////			else
////			{
////				attributes.addFlashAttribute("mensagem", "CPF ou senha incorretas! Tente novamente.");
////				
////				return new ModelAndView("redirect:/login");
////			}
//			
//			
//		
//	}
}
