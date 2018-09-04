/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.repository;

import com.projeto.estacionai.model.ContaPagar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alisson
 */
@Repository
public interface ContaPagarRepository extends JpaRepository<ContaPagar, Long> {

    public ContaPagar findFirstByOrderByIdDesc();
    
    @Query(value = "SELECT SUM(valor) FROM conta_pagar", nativeQuery = true)
    public Double getTotal();
    
    public List<ContaPagar> findByAtivoTrue();

}
