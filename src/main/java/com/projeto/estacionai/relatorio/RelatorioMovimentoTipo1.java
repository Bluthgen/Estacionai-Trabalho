package com.projeto.estacionai.relatorio;

public class RelatorioMovimentoTipo1 {
	
	public RelatorioMovimentoTipo1() {
		// TODO Auto-generated constructor stub
	}
	
	
	public RelatorioMovimentoTipo1(Integer tipoVeiculo, Integer qtdMovimento) {
		super();
		this.tipoVeiculo = tipoVeiculo;
		this.qtdMovimento = qtdMovimento;
	}



	
	private Integer tipoVeiculo;
	
	private Integer qtdMovimento;

  

	public Integer getTipoVeiculo() {
		return tipoVeiculo;
	}


	public void setTipoVeiculo(Integer tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}


	public Integer getQtdMovimento() {
		return qtdMovimento;
	}

	public void setQtdMovimento(Integer qtdMovimento) {
		this.qtdMovimento = qtdMovimento;
	}
	
	

}
