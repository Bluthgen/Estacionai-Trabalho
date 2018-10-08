/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Eduardo
 */
@Entity
public class Vaga {
	
	public Vaga(){}
    
    public Vaga(@NotNull Boolean ocupada, @NotNull Bloco bloco) {
	super();
	this.ocupada = ocupada;
	this.bloco = bloco;
	}
	    
	 public Vaga(Bloco bloco){
		this.ocupada = false;
	            if(bloco.addVaga(this))
	                this.bloco = bloco;
	}
	 
	 
	
	public Vaga(@NotNull boolean ocupada, @NotNull Bloco bloco, @NotNull Integer tipo) {
		super();
		this.ocupada = ocupada;
		this.bloco = bloco;
		this.tipo = tipo;
	}



	// Atributos
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
    private boolean ocupada;
	
	@NotNull
	@OneToOne
	@JoinColumn(name="bloco_id")
    private Bloco bloco;
	
	@NotNull
	private Integer tipo;
        
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
        
        public boolean getOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}
        
        public Bloco getBloco() {
		return bloco;
	}

	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}
	
        public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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
		Vaga other = (Vaga) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
