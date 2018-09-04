/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.repository;

import com.projeto.estacionai.model.Relatorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alisson
 */
@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {

	public List<Relatorio> findByAtivoTrue();
}
