package com.jacaranda.miprimeraapi.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jacaranda.miprimeraapi.model.Users;
import com.jacaranda.miprimeraapi.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserRepository repository;


	/*
	Para añadir el usuario le pasamos el usuario y la url de la aplicación
	que debe ir en el mensaje
	*/
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user=repository.findById(username).orElse(null);
		if (user==null) {
			throw new UsernameNotFoundException("User not found");
		}
		return user;
	}
}
