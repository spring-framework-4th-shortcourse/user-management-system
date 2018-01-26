package com.kshrd.springbootdemo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password("123").roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http	
			.csrf().disable();
		http
			.httpBasic();
		http
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http
			.authorizeRequests()
			.anyRequest().authenticated();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/v2/api-docs", 
				"/swagger-resources/**", 
				"/swagger-ui.html/**", 
				"/webjars/**");
		
		//Enable Preflight request
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
		
	}
	
}
