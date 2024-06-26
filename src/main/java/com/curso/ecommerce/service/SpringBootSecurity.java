package com.curso.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringBootSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailService).passwordEncoder(getEnecoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
        http.csrf(csrf -> csrf.disable())
    		.authorizeRequests(requests -> requests
    				.antMatchers("/administrador/**").hasRole("ADMIN")
    				.antMatchers("/productos/**").hasRole("ADMIN"))
			.formLogin(login -> login
					.loginPage("/usuario/login").permitAll()
					.defaultSuccessUrl("/usuario/acceder"));
	}
	
	@Bean
    BCryptPasswordEncoder getEnecoder() {
		
		return new BCryptPasswordEncoder(); 
	}
	
}
