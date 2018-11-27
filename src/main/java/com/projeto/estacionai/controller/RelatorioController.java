/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.controller;

import com.projeto.estacionai.model.ContaEquipamento;
import com.projeto.estacionai.model.ContaPagar;
import com.projeto.estacionai.model.ContaReceber;
import com.projeto.estacionai.model.Equipamento;
import com.projeto.estacionai.model.MovimentoCliente;
import com.projeto.estacionai.repository.EquipamentoRepositorySearch;
import com.projeto.estacionai.repository.RelatorioContaEquipamentoRepositorySearch;
import com.projeto.estacionai.repository.RelatorioContaPagarRepositorySearch;
import com.projeto.estacionai.repository.RelatorioContaReceberRepositorySearch;
import com.projeto.estacionai.security.Conexao;
import com.projeto.estacionai.service.ContaEquipamentoService;
import com.projeto.estacionai.service.ContaPagarService;
import com.projeto.estacionai.service.ContaReceberService;
import com.projeto.estacionai.service.FuncionarioService;
import com.projeto.estacionai.service.MovimentoClienteService;
import com.projeto.estacionai.util.GeradorRelatorio;

import java.io.File;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 *
 * @author Alisson
 */
@Controller
@RequestMapping("/relatorios")
public class RelatorioController {
	
	@Autowired
	private ContaPagarService servicePagar;
	@Autowired
	private ContaReceberService serviceReceber;
	@Autowired
	private ContaEquipamentoService serviceEquipamento;
	@Autowired
	private RelatorioContaPagarRepositorySearch searchPagar;
	@Autowired
	private RelatorioContaReceberRepositorySearch searchReceber;
	@Autowired
	private EquipamentoRepositorySearch searchEquipamento;
	@Autowired
	private RelatorioContaEquipamentoRepositorySearch searchEquipamentoConta;
	@Autowired
	private FuncionarioService serviceFunc;
	
	@GetMapping("/pagar")
	public ModelAndView index(ContaPagar filtro)
	{		
		
		ModelAndView mv = new ModelAndView("relatorios/pagar/v-relatorio");
//		mv.addObject("contas", servicePagar.buscarTodos());
//		mv.addObject("total", servicePagar.total());

		filtro.setAtivo(true);
		mv.addObject("filtro", filtro);
		mv.addObject("contas", searchPagar.filtrar(filtro));
		mv.addObject("filtroPdf", new ContaPagar());
		mv.addObject("total", servicePagar.total());
		mv.addObject("user", serviceFunc.buscarUser(
				SecurityContextHolder.getContext().getAuthentication().getName()));
		return mv;
	}
	
	@PostMapping("/pagar")
	public ModelAndView listarEspecifico(ContaPagar filtro)
	{		
		return index(filtro);
	}
	
	@PostMapping("/pagar/gerarPdf")
	public ModelAndView gerarPdf(ContaPagar filtro)
	{		
		try {
			String nomeArquivo = "relatorio_pagar.jasper";
			String caminho = new File("./").getAbsolutePath(); 
			//System.err.println("CAMINHO GERADO:"+caminho);
			caminho = caminho.substring(0, caminho.length() - 1);
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			
			params.put("DATA_INICIO", Date.valueOf(filtro.getDataInicio()));
			params.put("DATA_FIM", Date.valueOf(filtro.getDataFim()));
			GeradorRelatorio geradorRelatorio = new GeradorRelatorio(nomeArquivo, params, Conexao.getConnection());
			geradorRelatorio.gerarPDFParaOutputStream(new SimpleOutputStreamExporterOutput(caminho+"conta_pagar.pdf"));
				
			System.out.println("Foi gerado");
			return new ModelAndView("redirect:/relatorios/pagar").addObject("gerado", true);
		}catch (Exception e) {
			
		}
		
		return new ModelAndView("redirect:/relatorios/pagar").addObject("error", true);
	
	}
	
	@GetMapping("/receber")
	public ModelAndView indexReceber(ContaReceber filtro)
	{		
		
		ModelAndView mv = new ModelAndView("relatorios/receber/v-relatorio");
//		mv.addObject("contas", serviceReceber.buscarTodos());
//		mv.addObject("total", serviceReceber.total());
		filtro.setAtivo(true);
		mv.addObject("filtro", filtro);
		mv.addObject("contas", searchReceber.filtrar(filtro));
		mv.addObject("filtroPdf", new ContaReceber());
		mv.addObject("total", serviceReceber.total());
		mv.addObject("user", serviceFunc.buscarUser(
				SecurityContextHolder.getContext().getAuthentication().getName()));
		return mv;
	}
	
	@PostMapping("/receber")
	public ModelAndView listarEspecificoReceber(ContaReceber filtro)
	{		
		return indexReceber(filtro);
	}
	
	@PostMapping("/receber/gerarPdf")
	public ModelAndView gerarPdf(ContaReceber filtro)
	{		
		try {
			String nomeArquivo = "relatorio_receber.jasper";
			String caminho = new File("./").getAbsolutePath(); 
			//System.err.println("CAMINHO GERADO:"+caminho);
			caminho = caminho.substring(0, caminho.length() - 1);
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			
			params.put("DATA_INICIO", Date.valueOf(filtro.getDataInicio()));
			params.put("DATA_FIM", Date.valueOf(filtro.getDataFim()));
			GeradorRelatorio geradorRelatorio = new GeradorRelatorio(nomeArquivo, params, Conexao.getConnection());
			geradorRelatorio.gerarPDFParaOutputStream(new SimpleOutputStreamExporterOutput(caminho+"contas_receber.pdf"));
				
			System.out.println("Foi gerado");
			return new ModelAndView("redirect:/relatorios/receber").addObject("gerado", true);
		}catch (Exception e) {
			
		}
		
		return new ModelAndView("redirect:/relatorios/receber").addObject("error", true);
	
	}
	
	@GetMapping("/movimento")
	public ModelAndView indexMovimento(MovimentoCliente filtro)
	{		
		
		ModelAndView mv = new ModelAndView("relatorios/movimento/v-relatorio");
		mv.addObject("filtro", filtro);
		mv.addObject("filtroPdf", new MovimentoCliente());
		mv.addObject("contas", serviceReceber.buscarTodos());
		mv.addObject("total", serviceReceber.total());
		mv.addObject("user", serviceFunc.buscarUser(
				SecurityContextHolder.getContext().getAuthentication().getName()));
		return mv;
	}
	
	@PostMapping("/movimento/gerarPdf")
	public ModelAndView gerarPdf(MovimentoCliente filtro)
	{		
		try {
			String caminho = new File("./").getAbsolutePath();
			String nomeArquivo = "relatorio_movimento_cliente.jasper";
			 
			//System.err.println("CAMINHO GERADO:"+caminho);
			caminho = caminho.substring(0, caminho.length() - 1);
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			
			params.put("DATA_INICIO", Date.valueOf(filtro.getDataInicio()));
			params.put("DATA_FIM", Date.valueOf(filtro.getDataFim()));
			GeradorRelatorio geradorRelatorio = new GeradorRelatorio(nomeArquivo, params, Conexao.getConnection());
			geradorRelatorio.gerarPDFParaOutputStream(new SimpleOutputStreamExporterOutput(caminho+"movimento_cliente.pdf"));
				
			System.out.println("Foi gerado");
			return new ModelAndView("redirect:/relatorios/movimento").addObject("gerado", true);
		}catch (Exception e) {
			
		}
		
		return new ModelAndView("redirect:/relatorios/movimento").addObject("error", true);
	
	}
	
	
	@GetMapping("/equipamento/conta")
	public ModelAndView indexContaEquipamento(ContaEquipamento filtro)
	{		
		
		ModelAndView mv = new ModelAndView("relatorios/equipamento/conta/v-relatorio");
//		mv.addObject("contas", serviceEquipamento.buscarTodos());
		filtro.setAtivo(true);
		mv.addObject("filtro", filtro);
		mv.addObject("filtroPdf", new ContaEquipamento());
		mv.addObject("contas", searchEquipamentoConta.filtrar(filtro));
		mv.addObject("total", serviceEquipamento.total());
		mv.addObject("user", serviceFunc.buscarUser(
				SecurityContextHolder.getContext().getAuthentication().getName()));
		return mv;
	}
	
	@PostMapping("/equipamento/conta")
	public ModelAndView listarEspecificoContaEquipamento(ContaEquipamento filtro)
	{		
		return indexContaEquipamento(filtro);
	}
	
	@PostMapping("/equipamento/conta/gerarPdf")
	public ModelAndView gerarPdf(ContaEquipamento filtro)
	{		
		try {
			String nomeArquivo = "relatorio_equipamento_conta.jasper";
			String caminho = new File("./").getAbsolutePath(); 
			//System.err.println("CAMINHO GERADO:"+caminho);
			caminho = caminho.substring(0, caminho.length() - 1);
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			
			params.put("DATA_INICIO", Date.valueOf(filtro.getDataInicio()));
			params.put("DATA_FIM", Date.valueOf(filtro.getDataFim()));
			GeradorRelatorio geradorRelatorio = new GeradorRelatorio(nomeArquivo, params, Conexao.getConnection());
			geradorRelatorio.gerarPDFParaOutputStream(new SimpleOutputStreamExporterOutput(caminho+"conta_equipamento.pdf"));
				
			System.out.println("Foi gerado");
			return new ModelAndView("redirect:/relatorios/equipamento/conta").addObject("gerado", true);
		}catch (Exception e) {
			
		}
		
		return new ModelAndView("redirect:/relatorios/equipamento/conta").addObject("error", true);
	
	}
	
	@GetMapping("/equipamento/status")
	public ModelAndView indexEquipamento(Equipamento filtro)
	{		
		
		ModelAndView mv = new ModelAndView("relatorios/equipamento/status/v-relatorio");
		filtro.setAtivo(true);
		mv.addObject("filtro", filtro);
		mv.addObject("filtroPdf", new Equipamento());
		mv.addObject("equipamentos", searchEquipamento.filtrar(filtro));
		mv.addObject("user", serviceFunc.buscarUser(
				SecurityContextHolder.getContext().getAuthentication().getName()));
		return mv;
	}
	
	@PostMapping("/equipamento/status")
	public ModelAndView listarEspecificoEquipamento(Equipamento filtro)
	{		
		return indexEquipamento(filtro);
	}
	
	@PostMapping("/equipamento/status/gerarPdf")
	public ModelAndView gerarPdf(Equipamento filtro)
	{		
		try {
			String nomeArquivo = "relatorio_equipamento_status.jasper";
			String caminho = new File("./").getAbsolutePath(); 
			//System.err.println("CAMINHO GERADO:"+caminho);
			caminho = caminho.substring(0, caminho.length() - 1);
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			String tipo= filtro.getTipo();
			
			if(tipo == null || tipo == "") {
				params.put("TIPO", "%");
			}else {
				params.put("TIPO", tipo);
			}
			GeradorRelatorio geradorRelatorio = new GeradorRelatorio(nomeArquivo, params, Conexao.getConnection());
			geradorRelatorio.gerarPDFParaOutputStream(new SimpleOutputStreamExporterOutput(caminho+"status_equipamento.pdf"));
				
			System.out.println("Foi gerado");
			return new ModelAndView("redirect:/relatorios/equipamento/status").addObject("gerado", true);
		}catch (Exception e) {
			
		}
		
		return new ModelAndView("redirect:/relatorios/equipamento/status").addObject("error", true);
	
	}

}
