/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.estacionai.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.projeto.estacionai.model.MovimentoCliente;
import com.projeto.estacionai.security.Conexao;

import org.springframework.stereotype.Component;

/**
 *
 * @author ALISSON
 */
@Component
public class MovimentoClienteRepositoryObserver {
    
    
	private final String INSERT_SQL = "INSERT INTO movimento_cliente(nome, data_movimento, ativo, tipo_veiculo) values(?,?,?,?)";

	private Connection conn;
	
	public MovimentoClienteRepositoryObserver()
	{
		this.conn = Conexao.getConnection();
	}

	public void save(MovimentoCliente mc) {
		try {
			PreparedStatement ps = this.conn.prepareStatement(INSERT_SQL);
			ps.setString(1, mc.getNome());
			ps.setString(2, mc.getDataMovimento().toString());
			ps.setBoolean(3, mc.getAtivo());
			ps.setInt(4, mc.getTipoVeiculo());
			
			ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}