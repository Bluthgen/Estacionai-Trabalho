/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.repository;

import com.projeto.estacionai.model.ContaEquipamento;
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

/**
 *
 * @author Alisson
 */
@Repository
public class ContaEquipamentoRepositorySearch {

	@PersistenceContext
	private EntityManager manager;
	
	
	public List<ContaEquipamento> filtrar(ContaEquipamento conta) {
		CriteriaBuilder builder =  manager.getCriteriaBuilder();
		CriteriaQuery<ContaEquipamento> criteria = builder.createQuery(ContaEquipamento.class);
		
		Root<ContaEquipamento> root = criteria.from(ContaEquipamento.class);
		
		Predicate[] predicates = restricoes(conta, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ContaEquipamento> query = manager.createQuery(criteria);
		
		return query.getResultList(); 
	}
	
	private Predicate[] restricoes(ContaEquipamento conta, CriteriaBuilder builder, Root<ContaEquipamento> root) {
		List<Predicate> predicates	= new ArrayList<>();
		
		
		if(conta.getId() != null)
			predicates.add(builder.equal(root.get("id"), 
					conta.getId()));
		if(conta.getNome() != null && !conta.getNome().isEmpty())
			predicates.add(builder.like(builder.lower(root.get("nome")), 
					"%"+  conta.getNome().toLowerCase()+ "%"));
		if(conta.getEstado() != null && !conta.getEstado().isEmpty())
			predicates.add(builder.like(builder.lower(root.get("estado")), 
					"%"+  conta.getEstado().toLowerCase()+ "%"));
		if(conta.getLugarReparo() != null && !conta.getLugarReparo().isEmpty())
			predicates.add(builder.like(builder.lower(root.get("lugarReparo")), 
					"%"+  conta.getLugarReparo().toLowerCase()+ "%"));
		if(conta.getValor() != null && conta.getValor() != 0)
			predicates.add(builder.equal(root.get("valor"), conta.getValor() ));
		if(conta.getDataVencimento() != null)
			predicates.add(builder.equal(root.get("dataVencimento"), conta.getDataVencimento() ));
		if(conta.getAtivo() != null)	
			predicates.add(builder.equal(root.get("ativo"), 
					conta.getAtivo()));
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
}

