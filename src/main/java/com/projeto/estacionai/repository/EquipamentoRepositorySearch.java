package com.projeto.estacionai.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projeto.estacionai.model.Equipamento;

@Repository
public class EquipamentoRepositorySearch {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Equipamento> filtrar(Equipamento Equipamento) {
		CriteriaBuilder builder =  manager.getCriteriaBuilder();
		CriteriaQuery<Equipamento> criteria = builder.createQuery(Equipamento.class);
		
		Root<Equipamento> root = criteria.from(Equipamento.class);
		
		Predicate[] predicates = restricoes(Equipamento, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Equipamento> query = manager.createQuery(criteria);
		
		return query.getResultList(); 
	}
}
