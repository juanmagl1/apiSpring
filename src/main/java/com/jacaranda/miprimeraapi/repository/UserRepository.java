package com.jacaranda.miprimeraapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.miprimeraapi.model.Users;


public interface UserRepository extends JpaRepository<Users, String> {
	List<Users> findByEmail(String email);
}
