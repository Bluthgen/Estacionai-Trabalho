/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.model;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class ContaEquipamento {
	
	public ContaEquipamento()
	{
		
	}

	public ContaEquipamento(@NotBlank String nome, @NotBlank String estado, @NotBlank String lugarReparo,
			@NotNull Double valor, Boolean ativo, @NotNull LocalDate dataVencimento) {
		super();
		this.nome = nome;
		this.estado = estado;
		this.lugarReparo = lugarReparo;
		this.valor = valor;
		this.ativo = ativo;
		this.dataVencimento = dataVencimento;
	}



	public ContaEquipamento(@NotBlank String nome, @NotBlank String estado, @NotBlank String lugarReparo, Boolean ativo,
		 @NotNull LocalDate dataVencimento) {
		super();
		this.nome = nome;
		this.estado = estado;
		this.lugarReparo = lugarReparo;
		this.ativo = ativo;
		this.dataVencimento = dataVencimento;
	}



	public ContaEquipamento(@NotBlank String nome, @NotBlank String estado, @NotBlank String lugarReparo) {
		super();
		this.nome = nome;
		this.estado = estado;
		this.lugarReparo = lugarReparo;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String estado;
	
	@NotBlank
	private String lugarReparo;
	
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
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Convert(converter = AdapterLocalDate.class)
	private LocalDate dataVencimento;

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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getLugarReparo() {
		return lugarReparo;
	}

	public void setLugarReparo(String lugarReparo) {
		this.lugarReparo = lugarReparo;
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
	
	
	
	
	
	
    
    
	
	
}