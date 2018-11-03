/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.controller;

import com.projeto.estacionai.model.Cliente;
import com.projeto.estacionai.model.ContaEquipamento;
import com.projeto.estacionai.model.ContaPagar;
import com.projeto.estacionai.model.ContaReceber;
import com.projeto.estacionai.model.MovimentoCliente;
import com.projeto.estacionai.relatorio.RelatorioMovimentoTipo1;
import com.projeto.estacionai.relatorio.RelatorioMovimentoTipo3;
import com.projeto.estacionai.repository.RelatorioContaEquipamentoRepositorySearch;
import com.projeto.estacionai.repository.RelatorioContaPagarRepositorySearch;
import com.projeto.estacionai.repository.RelatorioContaReceberRepositorySearch;
import com.projeto.estacionai.repository.RelatorioMovimentoClienteRepositorySearch;
import com.projeto.estacionai.security.Conexao;
import com.projeto.estacionai.service.ClienteService;
import com.projeto.estacionai.service.ContaEquipamentoService;
import com.projeto.estacionai.service.ContaPagarService;
import com.projeto.estacionai.service.ContaReceberService;
import com.projeto.estacionai.service.MovimentoClienteService;
import com.projeto.estacionai.util.GeradorRelatorio;

import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	private RelatorioContaEquipamentoRepositorySearch searchEquipamento;
	@Autowired
	private MovimentoClienteService serviceMovimento;
	@Autowired
	private RelatorioMovimentoClienteRepositorySearch searchMovimento;
	@Autowired
	private ClienteService serviceCliente;
	
	
	@GetMapping("/pagar")
	public ModelAndView index(ContaPagar filtro)
	{		
		
		ModelAndView mv = new ModelAndView("relatorios/pagar/v-relatorio");
//		mv.addObject("contas", servicePagar.buscarTodos());
//		mv.addObject("total", servicePagar.total());

		filtro.setAtivo(true);
		mv.addObject("filtro", filtro);
		mv.addObject("contas", searchPagar.filtrar(filtro));
		mv.addObject("total", servicePagar.total());
		return mv;
	}
	
	@PostMapping("/pagar")
	public ModelAndView listarEspecifico(ContaPagar filtro)
	{		
		return index(filtro);
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
		mv.addObject("total", serviceReceber.total());
		return mv;
	}
	
	@PostMapping("/receber")
	public ModelAndView listarEspecificoReceber(ContaReceber filtro)
	{	
		
		return indexReceber(filtro);
	}
	
	@GetMapping("/movimento")
	public ModelAndView indexMovimento(MovimentoCliente filtro, Integer tipoRelatorio)
	{		
		
		if(tipoRelatorio == null)
		{
			tipoRelatorio = 4;
		}
		
		ModelAndView mv = new ModelAndView("relatorios/movimento/v-relatorio");
		filtro.setAtivo(true);
		mv.addObject("filtro", filtro);
		mv.addObject("filtroPdf", new MovimentoCliente());
		mv.addObject("tipoPesquisa", tipoRelatorio);
		
		if(tipoRelatorio == 1)
		{
			filtro.setCliente(null);
			List<RelatorioMovimentoTipo1> lista= this.serviceMovimento.buscarMaisUtilizaramEstacionamento();
			mv.addObject("relatorio1", lista);
			
		}
		else if(tipoRelatorio == 2)
		{
			
			if(filtro.getCliente() != null && !filtro.getCliente().getCpf().equals(""))
			{
				Cliente cliente = this.serviceCliente.buscarPorCpf(filtro.getCliente().getCpf());
				filtro.setCliente(cliente);
				mv.addObject("relatorio2", searchMovimento.filtrar(filtro));
			}
			else
			{
				mv.addObject("relatorio2", new ArrayList<MovimentoCliente>());
			}
			
		}
		else if(tipoRelatorio == 3)
		{
			filtro.setCliente(null);
			List<RelatorioMovimentoTipo3> lista= this.serviceMovimento.buscarClientesMaisUtilizaramEstacionamento();
			mv.addObject("relatorio3", lista);
		}
		else if(tipoRelatorio == 4)
		{
			filtro.setCliente(null);
			mv.addObject("contas", searchMovimento.filtrar(filtro));
		}
		
		return mv;
	}
	
	@PostMapping("/movimento/gerarPdf")
	public ModelAndView gerarPdf(MovimentoCliente filtro)
	{		
		try {
			String nomeArquivo = "relatorio_movimento_cliente.jasper";
			String caminho = new File("./").getAbsolutePath(); 
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
	
	@PostMapping("/movimento")
	public ModelAndView listarEspecificoMovimento(MovimentoCliente filtro, @RequestParam("tipoRelatorio") Integer tipoRelatorio)
	{	
		return indexMovimento(filtro, tipoRelatorio);
	}
	
	
	@GetMapping("/equipamento")
	public ModelAndView indexEquipamento(ContaEquipamento filtro)
	{		
		
		ModelAndView mv = new ModelAndView("relatorios/equipamento/v-relatorio");
//		mv.addObject("contas", serviceEquipamento.buscarTodos());
		filtro.setAtivo(true);
		mv.addObject("filtro", filtro);
		mv.addObject("contas", searchEquipamento.filtrar(filtro));
		mv.addObject("total", serviceEquipamento.total());
		return mv;
	}
	
	@PostMapping("/equipamento")
	public ModelAndView listarEspecificoEquipamento(ContaEquipamento filtro)
	{		
		return indexEquipamento(filtro);
	}
	
	

}
