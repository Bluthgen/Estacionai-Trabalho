/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.projeto.estacionai.model.HistoricoEntradaSaida;
import com.projeto.estacionai.security.Conexao;

import org.springframework.stereotype.Component;

/**
 *
 * @author ALISSON
 */
@Component
public class HistoricoEntradaSaidaRepositoryObserver {
    
    
	private final String INSERT_SQL = "INSERT INTO historico_entrada_saida(codigo, horario_chegada, ativo, horario_saida) values(?,?,?,?)";

	private Connection conn;
	
	public HistoricoEntradaSaidaRepositoryObserver()
	{
		this.conn = Conexao.getConnection();
	}

	public void save(HistoricoEntradaSaida hes) {
		try {
			PreparedStatement ps = this.conn.prepareStatement(INSERT_SQL);
			ps.setString(1, hes.getCodigo());
			ps.setString(2, hes.getHorarioChegada().toString());
			ps.setBoolean(3, hes.getAtivo());
			ps.setString(4, hes.getHorarioSaida().toString());
			
			ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}