package com.jacaranda.miprimeraapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.miprimeraapi.model.Element;
import com.jacaranda.miprimeraapi.repository.ElementRepository;

@Service
public class ElementService {
	@Autowired
	ElementRepository repositorio;
	
	public Element add(Element e) {
		return repositorio.save(e);
	}
	
	public List<Element>getAll(){
		return repositorio.findAll();
	}
	
	public Element getElement(Integer id) {
		return repositorio.findById(id).orElse(null);
	}
	
	public void remove (Element s) {
		repositorio.delete(s);
	}
}
