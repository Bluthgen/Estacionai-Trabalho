/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.repository;

import com.projeto.estacionai.model.HistoricoEntradaSaida;
import com.projeto.estacionai.model.Ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ALISSON
 */
@Repository
public interface HistoricoEntradaSaidaRepository extends JpaRepository<HistoricoEntradaSaida, Long> {
    
    public Ticket findLastByCodigoLike(String codigo);
    
    public List<HistoricoEntradaSaida> findByAtivoTrue();

}
