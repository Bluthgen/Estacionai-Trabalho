package com.projeto.estacionai.model;

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
 * 
 * @author Gui
 *
 */

@Entity
public class Cliente {
	
	// Atributos
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String telefone;
	
	@NotBlank
	private String endereco;
	
	@NotBlank
	private String cpf;
	
	@NotNull
	private Integer tipoPagamento;
	
	@NotNull
	private Integer numeroVagas;
	
	@OneToMany(mappedBy="cliente")
	private List<Veiculo> veiculos;
	
	private Boolean ativo;
	
	private Integer numeroCartao;
	
	@NotBlank
	private String email;
	
	// Funções
	
	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public Cliente() {}
	
	public Cliente(String nome, String telefone, String endereco, String cpf, Integer tipoP, Integer numeroV){
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.cpf = cpf;
		this.tipoPagamento = tipoP;
		this.numeroVagas = numeroV;
	}
	
	
	
	public Cliente(@NotBlank String nome, @NotBlank String telefone, @NotBlank String endereco, @NotBlank String cpf,
			@NotNull Integer tipoPagamento, @NotNull Integer numeroVagas, @NotBlank String email, Integer numeroCartao) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.cpf = cpf;
		this.tipoPagamento = tipoPagamento;
		this.numeroVagas = numeroVagas;
		this.email = email;
		this.numeroCartao = numeroCartao;
	}

	public Cliente(@NotBlank String nome, @NotBlank String telefone, @NotBlank String endereco, @NotBlank String cpf,
			@NotNull Integer tipoPagamento, @NotNull Integer numeroVagas, List<Veiculo> veiculos, @NotBlank String email,
			Integer numeroCartao) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.cpf = cpf;
		this.tipoPagamento = tipoPagamento;
		this.numeroVagas = numeroVagas;
		this.veiculos = veiculos;
		this.email = email;
		this.numeroCartao = numeroCartao;
	}
	
	

	// Gets e Sets Cliente	

	public Cliente(@NotBlank String nome, @NotBlank String telefone, @NotBlank String endereco, @NotBlank String cpf,
			@NotNull Integer tipoPagamento, @NotNull Integer numeroVagas, Boolean ativo, @NotBlank String email) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.cpf = cpf;
		this.tipoPagamento = tipoPagamento;
		this.numeroVagas = numeroVagas;
		this.ativo = ativo;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(Integer tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Integer getNumeroVagas() {
		return numeroVagas;
	}

	public void setNumeroVagas(Integer numeroVagas) {
		this.numeroVagas = numeroVagas;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public Integer getnumeroCartao() {
		return numeroCartao;
	}
	
	public void setnumeroCartao(Integer numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	
	
}