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

import com.projeto.estacionai.model.Cliente;

@Repository
public class ClienteRepositorySearch {

	@PersistenceContext
	private EntityManager manager;
	
	
	public List<Cliente> filtrar(Cliente cliente) {
		CriteriaBuilder builder =  manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
		
		Root<Cliente> root = criteria.from(Cliente.class);
		
		Predicate[] predicates = restricoes(cliente, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Cliente> query = manager.createQuery(criteria);
		
		return query.getResultList(); 
	}
	
	private Predicate[] restricoes(Cliente cliente, CriteriaBuilder builder, Root<Cliente> root) {
		List<Predicate> predicates	= new ArrayList<>();
		
		if(!StringUtils.isEmpty(cliente.getEmail()))
			predicates.add(builder.like(builder.lower(root.get("email")), 
				cliente.getEmail().toLowerCase() ));
		if(!StringUtils.isEmpty(cliente.getCpf()))
			predicates.add(builder.like(builder.lower(root.get("cpf")), 
				cliente.getCpf().toLowerCase() ));
		if(!StringUtils.isEmpty(cliente.getEndereco()))
			predicates.add(builder.like(builder.lower(root.get("endereco")), 
					"%"+ cliente.getEndereco().toLowerCase()+ "%"));
		if(!StringUtils.isEmpty(cliente.getNome()))
			predicates.add(builder.like(builder.lower(root.get("nome")), 
					"%" + cliente.getNome().toLowerCase() + "%"));
		if(!StringUtils.isEmpty(cliente.getTelefone()))
			predicates.add(builder.like(builder.lower(root.get("telefone")), 
					"%" + cliente.getTelefone().toLowerCase() + "%"));
		if(cliente.getNumeroVagas() != null)
			predicates.add(builder.equal(root.get("numeroVagas"), 
					cliente.getNumeroVagas()));
		if(cliente.getTipoPagamento() != null)
			predicates.add(builder.equal(root.get("tipoPagamento"), 
					cliente.getTipoPagamento()));
		if(cliente.getAtivo() != null)	
			predicates.add(builder.equal(root.get("ativo"), 
					cliente.getAtivo()));
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
}
