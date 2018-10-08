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

import com.projeto.estacionai.model.Equipamento;

@Repository
public class EquipamentoRepositorySearch {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Equipamento> filtrar(Equipamento equipamento) {
		CriteriaBuilder builder =  manager.getCriteriaBuilder();
		CriteriaQuery<Equipamento> criteria = builder.createQuery(Equipamento.class);
		
		Root<Equipamento> root = criteria.from(Equipamento.class);
		
		Predicate[] predicates = restricoes(equipamento, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Equipamento> query = manager.createQuery(criteria);
		
		return query.getResultList(); 
	}
	
	private Predicate[] restricoes(Equipamento equipamento, CriteriaBuilder builder, Root<Equipamento> root) {
		List<Predicate> predicates	= new ArrayList<>();
		
		
		if(equipamento.getId() != null)
			predicates.add(builder.equal(root.get("id"), 
					equipamento.getId()));
		if(equipamento.getTipo() != null && !equipamento.getTipo().isEmpty())
			predicates.add(builder.like(builder.lower(root.get("tipo")), 
					"%"+  equipamento.getTipo().toLowerCase()+ "%"));
		if(equipamento.getStatus() != null && !equipamento.getStatus().isEmpty())
			predicates.add(builder.like(builder.lower(root.get("status")), 
					"%"+  equipamento.getStatus().toLowerCase()+ "%"));
		if(equipamento.getDescricao() != null && !equipamento.getDescricao().isEmpty())
			predicates.add(builder.like(builder.lower(root.get("descricao")), 
					"%"+  equipamento.getDescricao().toLowerCase()+ "%"));
		if(equipamento.getAtivo() != null)	
			predicates.add(builder.equal(root.get("ativo"), 
					equipamento.getAtivo()));
		
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
