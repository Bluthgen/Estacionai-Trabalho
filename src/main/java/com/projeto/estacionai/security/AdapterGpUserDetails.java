package com.projeto.estacionai.security;

import com.projeto.estacionai.model.Funcionario;


public interface AdapterGpUserDetails {

	GpUserDetails adapterGpUserDetails(Funcionario usuario);
	
}
