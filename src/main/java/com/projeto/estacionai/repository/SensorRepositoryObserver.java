/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projeto.estacionai.model.Bloco;
import com.projeto.estacionai.model.Sensor;
import com.projeto.estacionai.model.Vaga;
import com.projeto.estacionai.security.Conexao;

import org.springframework.stereotype.Component;

/**
 *
 * @author ALISSON
 */
@Component
public class SensorRepositoryObserver {
    

	
	private final String BUSCAR_TODOS = "SELECT * FROM sensor WHERE ativo = 1";

	private Connection conn;
	
	public SensorRepositoryObserver()
	{
		this.conn = Conexao.getConnection();
	}
	
	public List<Sensor> buscarTodos() {
		
		try {
			PreparedStatement ps = this.conn.prepareStatement(BUSCAR_TODOS);
			ResultSet rs = ps.executeQuery();
			
			List<Sensor> sensores = new ArrayList<>();
			
			while(rs.next())
			{
				Sensor retorno = new Sensor();
				retorno.setId(rs.getLong("id"));
				retorno.setAtivo(rs.getBoolean("ativo"));
				retorno.setLigada(rs.getBoolean("ligada"));
				Vaga vaga = new Vaga();
				vaga.setId(rs.getLong("vaga_id"));
				retorno.setVaga(vaga);
				
				sensores.add(retorno);
			}
			
			
			

			return sensores;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
			
	}



}
