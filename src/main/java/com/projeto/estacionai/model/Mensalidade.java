package com.projeto.estacionai.model;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import com.projeto.estacionai.util.AdapterLocalDate;

@Entity
public class Mensalidade {

	public Mensalidade(Long id, @NotNull LocalDate dataVencimento, @NotNull Double valor, @NotNull Long idCliente,
			@NotNull String status, boolean ativo) {
		super();
		this.id = id;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.idCliente = idCliente;
		this.status = status;
		this.ativo = ativo;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Convert(converter = AdapterLocalDate.class)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate dataVencimento;
	
	@NotNull
	private Double valor;
	
	@NotNull
	private Long idCliente;
	
	@NotNull
	private String status;
	
	private Boolean ativo;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
