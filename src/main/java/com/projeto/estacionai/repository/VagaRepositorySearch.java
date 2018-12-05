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

import com.projeto.estacionai.model.Vaga;

@Repository
public class VagaRepositorySearch {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Vaga> filtrar(Vaga vaga){
		CriteriaBuilder builder=  manager.getCriteriaBuilder();
		CriteriaQuery<Vaga> criteria= builder.createQuery(Vaga.class);
		
		Root<Vaga> root= criteria.from(Vaga.class);
		Predicate[] predicates= restricoes(vaga, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Vaga> query= manager.createQuery(criteria);
		
		return query.getResultList(); 
	}
	
	private Predicate[] restricoes(Vaga vaga, CriteriaBuilder builder, Root<Vaga> root) {
		List<Predicate> predicates	= new ArrayList<>();
		
		if(vaga.getBloco() != null)
			predicates.add(builder.equal(root.get("bloco"), 
					vaga.getBloco().getId()));
		if(vaga.getAtivo() != null)
			predicates.add(builder.equal(root.get("ativo"), 
					vaga.getAtivo()));
		if(vaga.getId() != null)
			predicates.add(builder.equal(root.get("id"), 
					vaga.getId()));
		if(vaga.getTipo() != null)
			predicates.add(builder.equal(root.get("tipo"), 
					vaga.getTipo()));
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
