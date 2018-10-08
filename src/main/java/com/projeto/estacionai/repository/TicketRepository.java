/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.repository;

import com.projeto.estacionai.model.Ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ALISSON
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
	//buscar o ultimo registro com a placa informada 
    public Ticket findLastByPlacaLike(String placa);
    
    public Ticket findLastByCodigoLike(String codigo);
    
    public List<Ticket> findByAtivoTrue();
    
    public Ticket findFirstByPlacaOrderByIdDesc(String placa);
    
    public Ticket findFirstByOrderByIdDesc();

}
