package com.projeto.estacionai.model;


import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.projeto.estacionai.util.AdapterLocalDate;

/**
 * 
 * 
 * @author Alisson
 *
 */

@Entity
public class MovimentoCliente {
	
	

	public MovimentoCliente(@NotBlank String nome, @NotNull Integer tipoVeiculo, @NotNull LocalDate dataMovimento) {
		super();
		this.nome = nome;
		this.tipoVeiculo = tipoVeiculo;
		this.dataMovimento = dataMovimento;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private Integer tipoVeiculo;
	
	private Boolean ativo;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Convert(converter = AdapterLocalDate.class)
	private LocalDate dataMovimento;
	

	public MovimentoCliente() {}

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

	public Integer getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(Integer tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public LocalDate getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(LocalDate dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}


	
	
	
	
}