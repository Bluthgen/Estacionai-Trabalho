package com.projeto.estacionai.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.projeto.estacionai.model.Funcionario;

@Repository
public class FuncionarioRepositorySearch {

	@PersistenceContext
	private EntityManager manager;
	
	
	public List<Funcionario> filtrar(Funcionario funcionario) {
		CriteriaBuilder builder =  manager.getCriteriaBuilder();
		CriteriaQuery<Funcionario> criteria = builder.createQuery(Funcionario.class);
		
		Root<Funcionario> root = criteria.from(Funcionario.class);
		
		Predicate[] predicates = restricoes(funcionario, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Funcionario> query = manager.createQuery(criteria);
		
		return query.getResultList(); 
	}
	
	private Predicate[] restricoes(Funcionario funcionario, CriteriaBuilder builder, Root<Funcionario> root) {
		List<Predicate> predicates	= new ArrayList<>();
		
		
		if(!StringUtils.isEmpty(funcionario.getCpf()))
			predicates.add(builder.like(builder.lower(root.get("cpf")), 
				funcionario.getCpf().toLowerCase() ));
		if(!StringUtils.isEmpty(funcionario.getPis()))
			predicates.add(builder.like(builder.lower(root.get("pis")), 
					"%"+ funcionario.getPis().toLowerCase()+ "%"));
		if(!StringUtils.isEmpty(funcionario.getNome()))
			predicates.add(builder.like(builder.lower(root.get("nome")), 
					"%" + funcionario.getNome().toLowerCase() + "%"));
		if(!StringUtils.isEmpty(funcionario.getTelefone()))
			predicates.add(builder.like(builder.lower(root.get("telefone")), 
					"%" + funcionario.getTelefone().toLowerCase() + "%"));
		if(!StringUtils.isEmpty(funcionario.getFuncao()))
			predicates.add(builder.like(builder.lower(root.get("funcao")), 
					"%"+ funcionario.getFuncao().toLowerCase()+ "%"));
		if(funcionario.getNivelPermissao() != null)
			predicates.add(builder.equal(root.get("nivelPermissao"), 
					funcionario.getNivelPermissao()));
		if(!StringUtils.isEmpty(funcionario.getSenha()))
			predicates.add(builder.equal(root.get("senha"), 
					funcionario.getSenha()));
		if(funcionario.getAtivo() != null)	
			predicates.add(builder.equal(root.get("ativo"), 
					funcionario.getAtivo()));
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
}