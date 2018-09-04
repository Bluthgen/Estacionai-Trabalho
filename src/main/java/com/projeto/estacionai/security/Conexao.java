package com.projeto.estacionai.security;

import java.sql.SQLException;


import org.springframework.stereotype.Component;

import java.sql.DriverManager;
import java.sql.Connection;
/**
 * @author Alisson
 */
@Component
public class Conexao {
	
private static Connection connection;
    
    private static String dsn = "jdbc:mysql://localhost:3306/estacionai?useSSL=false&zeroDateTimeBehavior=convertToNull";
    private static String username = "root";
    private static String password = "";
    
    
    public static synchronized Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(dsn, username, password);
            } catch (SQLException ex) {
                System.out.println("Houve um erro ao conectar com o Banco de Dados.");
            }
        }
        
        return connection;
    }
}
