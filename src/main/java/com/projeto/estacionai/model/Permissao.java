package com.projeto.estacionai.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.Set;







@Entity
public class Permissao{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @NotEmpty
    @Column(unique = true)
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissoes")
    private Set<Funcionario> usuarios;


    public Permissao() {};
    
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

    public Set<Funcionario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Funcionario> usuarios) {
        this.usuarios = usuarios;
    }

}
