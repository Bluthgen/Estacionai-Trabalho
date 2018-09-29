package com.projeto.estacionai.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private GpUserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
			.antMatchers(
                    "/layout/**",
                    "/stylesheets/**",
                    "/javascripts/**",
                    "/images/**",
                    "/webjars/**").permitAll()
				.antMatchers("/funcionarios**", "/relatorios/**", "/contas/**").hasAnyRole("GERENTE")
				.antMatchers("/home**", "/clientes**", "/blocos**").hasAnyRole("NORMAL", "GERENTE")
				.anyRequest() //para qualquer requisição
				.authenticated()//usuário precisa estar autenticado
				
				
			.and()
			.formLogin()
				.loginPage("/login")
				.passwordParameter("senha")
				.usernameParameter("login")
				.defaultSuccessUrl("/home")
				.failureUrl("/login/erro")
				.permitAll() //permita todos acessar a página de login
			.and()
			.logout()
				.logoutSuccessUrl("/login?logout")// caso o usuário dê logout
				.permitAll();
//			.and()
//			.rememberMe()
//				.userDetailsService(userDetailsService);
	}



}
