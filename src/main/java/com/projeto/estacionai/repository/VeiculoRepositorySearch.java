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

import com.projeto.estacionai.model.Veiculo;

@Repository
public class VeiculoRepositorySearch {

	@PersistenceContext
	private EntityManager manager;
	
	
	public List<Veiculo> filtrar(Veiculo veiculo) {
		CriteriaBuilder builder =  manager.getCriteriaBuilder();
		CriteriaQuery<Veiculo> criteria = builder.createQuery(Veiculo.class);
		
		Root<Veiculo> root = criteria.from(Veiculo.class);
		
		Predicate[] predicates = restricoes(veiculo, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Veiculo> query = manager.createQuery(criteria);
		
		return query.getResultList(); 
	}
	
	private Predicate[] restricoes(Veiculo veiculo, CriteriaBuilder builder, Root<Veiculo> root) {
		List<Predicate> predicates	= new ArrayList<>();
		
		
		if(!StringUtils.isEmpty(veiculo.getPlaca()))
			predicates.add(builder.like(builder.lower(root.get("placa")), 
					"%"+ veiculo.getPlaca().toLowerCase() + "%"));
		if(!StringUtils.isEmpty(veiculo.getRenavam()))
			predicates.add(builder.like(builder.lower(root.get("renavam")), 
					"%"+ veiculo.getRenavam().toLowerCase() + "%"));
		if(!StringUtils.isEmpty(veiculo.getModelo()))
			predicates.add(builder.like(builder.lower(root.get("modelo")), 
					"%"+ veiculo.getModelo().toLowerCase()+ "%"));
		if(!StringUtils.isEmpty(veiculo.getTipo()))
			predicates.add(builder.equal(builder.lower(root.get("tipo")), 
					veiculo.getTipo().toLowerCase()));
		if(veiculo.getAno() != null)
			predicates.add(builder.equal(root.get("ano"), 
					veiculo.getAno()));
		if(veiculo.getAtivo() != null)	
			predicates.add(builder.equal(root.get("ativo"), 
					veiculo.getAtivo()));
		if(veiculo.getCliente() != null && veiculo.getCliente().getId() != 0)
			predicates.add(builder.equal(root.get("cliente"), 
					veiculo.getCliente().getId()));
		
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
}
