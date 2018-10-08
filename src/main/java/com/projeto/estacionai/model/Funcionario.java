package com.projeto.estacionai.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 
 * 
 * @author Eduardo
 *
 */

@Entity
public class Funcionario {
	
	public Funcionario(@NotBlank String nome, @NotBlank String telefone, @NotBlank String funcao, @NotBlank String cpf,
			@NotBlank String pis, @NotNull Integer nivelPermissao, @NotBlank String senha, @NotBlank String senhaConfirm, @NotBlank String login,
			Boolean ativo, Set<com.projeto.estacionai.model.Permissao> permissoes) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.funcao = funcao;
		this.cpf = cpf;
		this.pis = pis;
		this.nivelPermissao = nivelPermissao;
		this.senha = senha;
		this.senhaConfirm = senhaConfirm;
		this.login = login;
		this.ativo = ativo;
		this.permissoes = permissoes;
	}


	public Funcionario(@NotBlank String nome, @NotBlank String telefone, @NotBlank String funcao, @NotBlank String cpf,
			@NotBlank String pis, @NotNull Integer nivelPermissao) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.funcao = funcao;
		this.cpf = cpf;
		this.pis = pis;
		this.nivelPermissao = nivelPermissao;
	}


	public Funcionario(@NotBlank String nome, @NotBlank String telefone, @NotBlank String funcao, @NotBlank String cpf,
			@NotBlank String pis, @NotNull Integer nivelPermissao, @NotBlank String senha) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.funcao = funcao;
		this.cpf = cpf;
		this.pis = pis;
		this.nivelPermissao = nivelPermissao;
		this.senha = senha;
	}

	public Funcionario(@NotBlank String nome, @NotBlank String telefone, @NotBlank String funcao, @NotBlank String cpf,
			@NotBlank String pis, @NotNull Integer nivelPermissao, @NotBlank String senha, @NotBlank String login,
			Boolean ativo) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.funcao = funcao;
		this.cpf = cpf;
		this.pis = pis;
		this.nivelPermissao = nivelPermissao;
		this.senha = senha;
		this.login = login;
		this.ativo = ativo;
	}




	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String telefone;
	
	@NotBlank
	private String funcao;
	
	@NotBlank
	private String cpf;
	
	@NotBlank
	private String pis;
	
	@NotNull
	private Integer nivelPermissao;
	
	@NotBlank
	private String senha;
	
	@NotBlank
	private String senhaConfirm;
	
	@NotBlank
	private String login;
	
	private Boolean ativo;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "funcionario_permissao",
            joinColumns =  @JoinColumn(name = "funcionario_id", referencedColumnName = "id"),
            inverseJoinColumns =  @JoinColumn(name = "permissao_id", referencedColumnName = "id"))
    private Set<Permissao> permissoes;
	
	// Funções

	public Funcionario() {}

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public Integer getNivelPermissao() {
		return nivelPermissao;
	}

	public void setNivelPermissao(Integer nivelPermissao) {
		this.nivelPermissao = nivelPermissao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenhaConfirm() {
		return senhaConfirm;
	}

	public void setSenhaConfirm(String senhaConfirm) {
		this.senhaConfirm = senhaConfirm;
	}


	public Boolean getAtivo() {
		return ativo;
	}


	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public Set<Permissao> getPermissoes() {
		return permissoes;
	}


	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	
	
	
	
}