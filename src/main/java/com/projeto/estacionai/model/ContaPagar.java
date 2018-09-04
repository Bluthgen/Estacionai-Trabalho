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

import com.projeto.estacionai.util.AdapterLocalDate;

/**
 *
 * @author Alisson
 */
@Entity
public class ContaPagar {
	
	public ContaPagar()
	{
		
	}

	public ContaPagar(@NotNull Integer tipoConta, @NotBlank String nome, @NotNull LocalDate dataVencimento, @NotNull Double valor) {
		super();
		this.nome = nome;
		this.tipoConta = tipoConta;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private Integer tipoConta;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Convert(converter = AdapterLocalDate.class)
	private LocalDate dataVencimento;
	
	@NotNull
	private Double valor;
	
	private Boolean ativo;
	
	@Transient
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Convert(converter = AdapterLocalDate.class)
	private LocalDate dataInicio;
	
	@Transient
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Convert(converter = AdapterLocalDate.class)
	private LocalDate dataFim;
	

	public Integer getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(Integer tipoConta) {
		this.tipoConta = tipoConta;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
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
	
	
    
    
	
	
}