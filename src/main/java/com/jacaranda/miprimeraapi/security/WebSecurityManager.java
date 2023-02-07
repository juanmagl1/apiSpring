package com.jacaranda.miprimeraapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jacaranda.miprimeraapi.service.UserService;
@Configuration
@EnableWebSecurity
public class WebSecurityManager {
	@Autowired
	private UserService myUserDetailService;

	// Indicamos que la configuración se hará a travéx del servicio.
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailService);
	}

	// Método que usaremos más abajo
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserService();
	}

	// Método que nos suministrará la codificación
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Método que autentifica
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	// Aquí es donde podemos especificar qué es lo que hace y lo que no
	// según el rol del usuario
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	http.csrf().disable()
	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	.and()
	.authorizeHttpRequests((requests) ->{
	requests
	.requestMatchers("/signin").permitAll()
	.anyRequest().permitAll();
	});
	return http.build();
	}
}
