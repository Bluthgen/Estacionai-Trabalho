/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.repository;

import com.projeto.estacionai.model.Bloco;
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
 * @author Eduardo
 */
@Repository
public class BlocoRepositorySearch {

	@PersistenceContext
	private EntityManager manager;
	
	
	public List<Bloco> filtrar(Bloco bloco) {
		CriteriaBuilder builder =  manager.getCriteriaBuilder();
		CriteriaQuery<Bloco> criteria = builder.createQuery(Bloco.class);
		
		Root<Bloco> root = criteria.from(Bloco.class);
		
		Predicate[] predicates = restricoes(bloco, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Bloco> query = manager.createQuery(criteria);
		
		return query.getResultList(); 
	}
	
	private Predicate[] restricoes(Bloco bloco, CriteriaBuilder builder, Root<Bloco> root) {
		List<Predicate> predicates	= new ArrayList<>();
		
		
		if(bloco.getId() != null)
			predicates.add(builder.equal(root.get("id"), 
					bloco.getId()));
        if(bloco.getNumVagas() != null)
			predicates.add(builder.equal(root.get("numVagas"), 
					bloco.getMaxVagas()));
		if(bloco.getMaxVagas() != null)
			predicates.add(builder.equal(root.get("maxVagas"), 
					bloco.getMaxVagas()));
		if(bloco.getNome() != null && !bloco.getNome().isEmpty())
			predicates.add(builder.like(builder.lower(root.get("nome")), 
					"%"+  bloco.getNome().toLowerCase()+ "%"));
		if(bloco.getAtivo() != null)	
			predicates.add(builder.equal(root.get("ativo"), 
					bloco.getAtivo()));
		
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
}

