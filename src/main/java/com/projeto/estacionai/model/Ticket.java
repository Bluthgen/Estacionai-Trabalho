/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.model;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.projeto.estacionai.util.LocalDateTimeAttributeConverter;

/**
 *
 * @author ALISSON
 */
@Entity
public class Ticket {

    public Ticket(@NotBlank String codigo, @NotBlank String placa, @NotNull Vaga vaga,
			@NotNull LocalDateTime horarioChegada, LocalDateTime horarioSaida, Double total) {
		super();
		this.codigo = codigo;
		this.placa = placa;
		this.vaga = vaga;
		this.horarioChegada = horarioChegada;
		this.horarioSaida = horarioSaida;
		this.total = total;
	}

	@Transient
	private static DecimalFormat df2 = new DecimalFormat(".##");
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String codigo;
	
	@NotBlank
	private String placa;
	
	@NotNull
	@OneToOne
	private Vaga vaga;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime horarioChegada;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime horarioSaida;
	
	private Double total;
	
	private Boolean ativo;
        
	
	public Ticket(){}    
	
	

	public Ticket(@NotBlank String placa, @NotNull Vaga vaga, @NotNull LocalDateTime horarioChegada,
			LocalDateTime horarioSaida, Double total) {
		super();
		this.placa = placa;
		this.vaga = vaga;
		this.horarioChegada = horarioChegada;
		this.horarioSaida = horarioSaida;
		this.total = total;
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

	public Vaga getVaga() {
		return vaga;
	}


	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}


	public LocalDateTime getHorarioChegada() {
		return horarioChegada;
	}


	public void setHorarioChegada(LocalDateTime horarioChegada) {
		this.horarioChegada = horarioChegada;
	}


	public LocalDateTime getHorarioSaida() {
		return horarioSaida;
	}


	public void setHorarioSaida(LocalDateTime horarioSaida) {
		this.horarioSaida = horarioSaida;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {	
		this.total = Double.parseDouble(df2.format(total));
	}

	

	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
		Ticket other = (Ticket) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
