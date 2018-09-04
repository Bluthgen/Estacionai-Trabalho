/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.repository;

import com.projeto.estacionai.model.ContaReceber;
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
public class RelatorioContaReceberRepositorySearch {

	@PersistenceContext
	private EntityManager manager;
	
	
	public List<ContaReceber> filtrar(ContaReceber conta) {
		CriteriaBuilder builder =  manager.getCriteriaBuilder();
		CriteriaQuery<ContaReceber> criteria = builder.createQuery(ContaReceber.class);
		
		Root<ContaReceber> root = criteria.from(ContaReceber.class);
		
		Predicate[] predicates = restricoes(conta, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ContaReceber> query = manager.createQuery(criteria);
		
		return query.getResultList(); 
	}
	
	private Predicate[] restricoes(ContaReceber conta, CriteriaBuilder builder, Root<ContaReceber> root) {
		List<Predicate> predicates	= new ArrayList<>();
			
		if(conta.getDataInicio() != null && conta.getDataFim() != null)	
		{
			predicates.add(builder.lessThanOrEqualTo(root.get("dataVencimento"), conta.getDataFim()));
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataVencimento"), conta.getDataInicio()));
		}
		if(conta.getAtivo() != null)	
			predicates.add(builder.equal(root.get("ativo"), 
					conta.getAtivo()));
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
}

