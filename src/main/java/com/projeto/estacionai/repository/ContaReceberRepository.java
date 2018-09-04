/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.repository;

import com.projeto.estacionai.model.ContaReceber;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alisson
 */
@Repository
public interface ContaReceberRepository extends JpaRepository<ContaReceber, Long> {

    public ContaReceber findFirstByOrderByIdDesc();
    
    @Query(value = "SELECT SUM(valor) FROM conta_receber", nativeQuery = true)
    public Double getTotal();
    
    public List<ContaReceber> findByAtivoTrue();

}
