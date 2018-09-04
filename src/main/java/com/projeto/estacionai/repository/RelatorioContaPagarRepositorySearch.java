/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.repository;

import com.projeto.estacionai.model.ContaPagar;
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
public class RelatorioContaPagarRepositorySearch {

	@PersistenceContext
	private EntityManager manager;
	
	
	public List<ContaPagar> filtrar(ContaPagar conta) {
		CriteriaBuilder builder =  manager.getCriteriaBuilder();
		CriteriaQuery<ContaPagar> criteria = builder.createQuery(ContaPagar.class);
		
		Root<ContaPagar> root = criteria.from(ContaPagar.class);
		
		Predicate[] predicates = restricoes(conta, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ContaPagar> query = manager.createQuery(criteria);
		
		return query.getResultList(); 
	}
	
	private Predicate[] restricoes(ContaPagar conta, CriteriaBuilder builder, Root<ContaPagar> root) {
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

