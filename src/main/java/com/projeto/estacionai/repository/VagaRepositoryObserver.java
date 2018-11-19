package com.projeto.estacionai.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.projeto.estacionai.model.Bloco;
import com.projeto.estacionai.model.Vaga;
import com.projeto.estacionai.security.Conexao;

import org.springframework.stereotype.Component;

@Component
public class VagaRepositoryObserver {
	
private final String INSERT_SQL = "UPDATE vaga SET ocupada = ? WHERE id = ?";
	
	private final String BUSCAR_ID = "SELECT * FROM vaga WHERE ativo = 1 AND id = ?";

	private Connection conn;
	
	public VagaRepositoryObserver()
	{
		this.conn = Conexao.getConnection();
	}
	
	public Vaga buscar(int id) {
		
		try {
			PreparedStatement ps = this.conn.prepareStatement(BUSCAR_ID);
			ps.setInt(1, id);			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Vaga retorno = new Vaga();
			retorno.setId(rs.getLong("id"));
			retorno.setAtivo(rs.getBoolean("ativo"));
			retorno.setOcupada(rs.getBoolean("ocupada"));
			retorno.setTipo(rs.getInt("tipo"));
			Bloco bloco = new Bloco();
			bloco.setId(rs.getLong("bloco_id"));
			retorno.setBloco(bloco);
			

			return retorno;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
			
	}

	public void save(Vaga vaga) {
		try {
			PreparedStatement ps = this.conn.prepareStatement(INSERT_SQL);
			ps.setBoolean(1, vaga.getOcupada());
			ps.setInt(2, vaga.getId().intValue());
			
			ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
