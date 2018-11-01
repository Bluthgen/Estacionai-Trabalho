/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.projeto.estacionai.util.AdapterLocalDate;

/**
 *
 * @author Alisson
 */
@Entity
public class ContaReceber {
	
	public ContaReceber()
	{
		
	}

	
	public ContaReceber(@NotBlank String nome, @NotNull LocalDate dataVencimento, @NotNull Double valor,
			Integer numeroParcelas, String numeroCartao, List<Parcela> parcelas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.numeroParcelas = numeroParcelas;
		this.numeroCartao = numeroCartao;
		this.parcelas = parcelas;
	}
	

	public List<Parcela> getParcelas() {
		return parcelas;
	}


	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Convert(converter = AdapterLocalDate.class)
	private LocalDate dataVencimento;


	private Integer numeroParcelas;
	
	
	private String numeroCartao;
	
	private Boolean ativo;
	
	@NotNull
	private Double valor;
	
	@Transient
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Convert(converter = AdapterLocalDate.class)
	private LocalDate dataInicio;
	
	@Transient
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Convert(converter = AdapterLocalDate.class)
	private LocalDate dataFim;
	
	@Transient
	private Boolean atrasada;
	
	public Double getValor() {
		return valor;
	}


	public void setValor(Double valores) {
		this.valor = valores;
	}


	@OneToMany(mappedBy="contaReceber")
	private List<Parcela> parcelas;



	public LocalDate getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}


	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}


	public String getNumeroCartao() {
		return numeroCartao;
	}


	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}


	public Boolean getAtivo() {
		return ativo;
	}


	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}


	public LocalDate getDataInicio() {
		return dataInicio;
	}


	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}


	public LocalDate getDataFim() {
		return dataFim;
	}


	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}


	public Boolean getAtrasada() {
		return atrasada;
	}


	public void setAtrasada(Boolean atrasada) {
		this.atrasada = atrasada;
	}

	
	
	
	
    
    
	
	
}