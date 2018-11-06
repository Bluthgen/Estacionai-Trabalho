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

import com.projeto.estacionai.model.Mensalidade;

@Repository
public class MensalidadeRepositorySearch {

	@PersistenceContext
	private EntityManager manager;
	
	
	public List<Mensalidade> filtrar(Mensalidade mensalidade) {
		CriteriaBuilder builder =  manager.getCriteriaBuilder();
		CriteriaQuery<Mensalidade> criteria = builder.createQuery(Mensalidade.class);
		
		Root<Mensalidade> root = criteria.from(Mensalidade.class);
		
		Predicate[] predicates = restricoes(mensalidade, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Mensalidade> query = manager.createQuery(criteria);
		
		return query.getResultList(); 
	}
	
	private Predicate[] restricoes(Mensalidade mensalidade, CriteriaBuilder builder, Root<Mensalidade> root) {
		List<Predicate> predicates	= new ArrayList<>();
		
			if(mensalidade.getId() != null)
				predicates.add(builder.equal(root.get("id"), 
						mensalidade.getId()));
			if(mensalidade.getAtivo() != null)	
				predicates.add(builder.equal(root.get("ativo"), 
						mensalidade.getAtivo()));
			if(mensalidade.getCliente() != null && mensalidade.getCliente().getId() != 0)
				predicates.add(builder.equal(root.get("cliente"),
						mensalidade.getCliente().getId()));
			if(mensalidade.getStatus() != null && !mensalidade.getStatus().isEmpty())
				predicates.add(builder.like(builder.lower(root.get("status")), 
						"%"+  mensalidade.getStatus().toLowerCase()+ "%"));
			if(mensalidade.getValor() != null)
				predicates.add(builder.equal(root.get("valor"), 
						mensalidade.getValor()));
			if(mensalidade.getDataVencimento() != null)
				predicates.add(builder.equal(root.get("dataVencimento"), 
						mensalidade.getDataVencimento()));
		
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
}
