package com.jacaranda.miprimeraapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.miprimeraapi.model.Element;

public interface ElementRepository extends JpaRepository<Element, Integer> {

}
