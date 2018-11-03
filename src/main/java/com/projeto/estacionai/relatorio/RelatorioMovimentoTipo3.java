package com.projeto.estacionai.relatorio;

public class RelatorioMovimentoTipo3 {
	
	public RelatorioMovimentoTipo3() {
		// TODO Auto-generated constructor stub
	}

	public RelatorioMovimentoTipo3(String nomeCliente, Integer qtdMovimento) {
		super();
		this.nomeCliente = nomeCliente;
		this.qtdMovimento = qtdMovimento;
	}



	private String nomeCliente;
	
	private Integer qtdMovimento;

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Integer getQtdMovimento() {
		return qtdMovimento;
	}

	public void setQtdMovimento(Integer qtdMovimento) {
		this.qtdMovimento = qtdMovimento;
	}
	
	
	
	

}
