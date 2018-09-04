package com.projeto.estacionai.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 
 * @author ALISSON 
 *
 */

@Entity
@Table(uniqueConstraints=
@UniqueConstraint(columnNames={"placa", "renavam"})
)
public class Veiculo {
	
	public Veiculo()
	{
		
	}
	
	
	

	public Veiculo(@NotBlank String placa, @NotBlank String modelo, @NotBlank String marca, @NotNull int ano,
			@NotBlank String tipo) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.ano = ano;
		this.tipo = tipo;
	}
	

	public Veiculo(@NotBlank String placa, @NotBlank String modelo, @NotBlank String marca, @NotNull Integer ano,
			@NotBlank String tipo, @NotBlank String renavam, Cliente cliente) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.ano = ano;
		this.tipo = tipo;
		this.renavam = renavam;
		this.cliente = cliente;
	}






	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String placa;
	
	@NotBlank
	private String modelo;
	
	@NotBlank
	private String marca;
	
	@NotNull
	private Integer ano;
	
	@NotBlank
	private String tipo;
	
	@NotBlank
	private String renavam;
	
	private Boolean ativo;
	
	
	@ManyToOne
	@JoinColumn(name="cliente_id", nullable=false)
	private Cliente cliente;
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public String getModelo() {
		return modelo;
	}




	public void setModelo(String modelo) {
		this.modelo = modelo;
	}




	public String getMarca() {
		return marca;
	}




	public void setMarca(String marca) {
		this.marca = marca;
	}




	public Integer getAno() {
		return ano;
	}




	public void setAno(Integer ano) {
		this.ano = ano;
	}


	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getRenavam() {
		return renavam;
	}




	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}


	


	public Boolean getAtivo() {
		return ativo;
	}




	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
