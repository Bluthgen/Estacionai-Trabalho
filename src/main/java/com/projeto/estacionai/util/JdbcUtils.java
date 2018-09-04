package com.projeto.estacionai.util;

public class JdbcUtils {
	
	public static final String USUARIO_POR_LOGIN = "SELECT login, senha, ativo, nome FROM funcionario"
			+ " WHERE login = ? AND ativo = 1";
	
	public static final String PERMISSOES_POR_USUARIO = "SELECT u.login, p.nome as nome_permissao FROM funcionario_permissao up"
			+ " JOIN funcionario u ON u.id = up.funcionario_id"
			+ " JOIN permissao p ON p.id = up.permissao_id"
			+ " WHERE u.login = ? AND u.ativo = 1";

}
