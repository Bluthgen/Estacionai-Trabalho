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
    
    
	private final String INSERT_SQL = "INSERT INTO movimento_cliente(nome, data_movimento, ativo, tipo_veiculo, cliente_id) values(?,?,?,?,?)";

	private Connection conn;
	
	public MovimentoClienteRepositoryObserver()
	{
		this.conn = Conexao.getConnection();
	}

	public void save(MovimentoCliente mc) {
		try {
			System.out.println("Entrei!");
			PreparedStatement ps = this.conn.prepareStatement(INSERT_SQL);
			System.out.println("Campos!");
			ps.setString(1, mc.getNome());
			System.out.println("Nome!");
			ps.setString(2, mc.getDataMovimento().toString());
			System.out.println("Data!");
			ps.setBoolean(3, mc.getAtivo());
			System.out.println("Ativo!");
			ps.setInt(4, mc.getTipoVeiculo());
			System.out.println("Tipo!");
			ps.setLong(5, mc.getCliente().getId());
			System.out.println("Id cliente!");
			ps.executeUpdate();			
			System.out.println("update!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
