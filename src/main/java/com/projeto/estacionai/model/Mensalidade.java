package com.projeto.estacionai.model;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.projeto.estacionai.util.AdapterLocalDate;

@Entity
public class Mensalidade {


	
	public Mensalidade(Long id, @NotNull LocalDate dataVencimento, @NotNull Double valor, Cliente cliente,
			@NotBlank String status, Boolean ativo) {
		super();
		this.id = id;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.cliente = cliente;
		this.status = status;
		this.ativo = ativo;
	}

	public Mensalidade() {
		
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
	
	@ManyToOne
	@JoinColumn(name="cliente_id", nullable=false)
	private Cliente cliente;
	
	@NotBlank
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}
