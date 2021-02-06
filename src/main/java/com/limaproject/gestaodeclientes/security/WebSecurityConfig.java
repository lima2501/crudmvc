package com.limaproject.gestaodeclientes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
						.authorizeRequests()
						.anyRequest()
						.authenticated()
						.and()
			 .formLogin()
			 			.loginPage("/login")
			 			.permitAll()
			 			.and()
			 .logout()
			 			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			 			.permitAll()
			 			.and()
			.rememberMe();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		
		builder.inMemoryAuthentication()
		   		   .withUser("alexandre").password("{noop}123").roles("EDITOR", "ADMIN")
		   		   .and()
		   		   .withUser("maria").password("{noop}321").roles("EDITOR");
	}
	
}
