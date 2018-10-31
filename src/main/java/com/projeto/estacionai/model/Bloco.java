/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Eduardo
 */
@Entity
public class Bloco {
	
	// Atributos

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	public Bloco(List<Vaga> vagas, @NotNull Integer maxVagas, Integer numVagas, @NotBlank String nome) {
		super();
		this.vagas = vagas;
		this.maxVagas = maxVagas;
		this.numVagas = numVagas;
		this.addVagas = 1;
		this.nome = nome;
	}

	@OneToMany(mappedBy = "bloco")
    private List<Vaga> vagas;
	
    @NotNull
    private Integer maxVagas;
        
    private Integer numVagas;
    
    @NotNull
    private Integer addVagas;
    
    
    @NotBlank
    private String nome;
    
    private Boolean ativo;
        
	public Bloco(){
    }
	
    public Bloco(Integer maxVagas){
    	this.vagas = new ArrayList<Vaga>();
        this.maxVagas = maxVagas;
        this.numVagas = 0;
        this.addVagas = 1;
    }
        
    public Bloco(List<Vaga> vagas, @NotNull Integer maxVagas,Integer numVagas) {
		super();
		this.vagas = vagas;
		this.maxVagas = maxVagas;
		this.numVagas = numVagas;
		this.addVagas = 1;
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
        
    public List<Vaga> getVagas() {
		return vagas;
	}
        
    public void setVagas(List<Vaga> vagas) {
    	this.vagas = vagas;
    }
        
	public Integer getNumVagas(){
        return numVagas;
    }
    
    public void setNumVagas(Integer numVagas){
        this.numVagas = numVagas;
    }

    public Integer getMaxVagas(){
        return maxVagas;
    }
    
    public void setMaxVagas(Integer maxVagas){
        this.maxVagas = maxVagas;
    }

    public Integer getAddVagas(){
        return addVagas;
    }
    
    public void setAddVagas(Integer addVagas){
        this.addVagas = addVagas;
    }

    
	public boolean addVaga(Vaga vaga){
        if(getNumVagas() < maxVagas){
            vagas.add(vaga);
            return true;
        }
        return false;
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
		Bloco other = (Bloco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}