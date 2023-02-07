package com.jacaranda.miprimeraapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.miprimeraapi.error.ApiError;
import com.jacaranda.miprimeraapi.error.ElementNotFoundException;
import com.jacaranda.miprimeraapi.model.Element;
import com.jacaranda.miprimeraapi.service.ElementService;

@RestController
public class ElementController {
	@Autowired
	ElementService servicio;

	@GetMapping("/element")
	public List<Element> findAll() {
		return servicio.getAll();
	}

	@GetMapping("/element/{id}")
	public ResponseEntity<Element> findById(@PathVariable Integer id) {
		Element elemento = servicio.getElement(id);
		if (elemento == null) {
			throw new ElementNotFoundException(id);
		} else {
			return ResponseEntity.ok(elemento);
		}

	}

	@ExceptionHandler(ElementNotFoundException.class)
	public ResponseEntity<ApiError> handleElementNotFoundException(ElementNotFoundException e) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

	@PutMapping("/element/{id}")
	public Element edit(@RequestBody Element elemento, @PathVariable Integer id) {
		if (servicio.getElement(id) != null) {
			elemento.setId(id);
			return servicio.add(elemento);
		} else {
			return null;
		}
	}

	@PostMapping("/element")
	public ResponseEntity<Element> add(@RequestBody Element elemento) {
		Element element = servicio.add(elemento);
		return ResponseEntity.status(HttpStatus.CREATED).body(element);

	}

	@DeleteMapping("/element/{id}")
	public Element del(@PathVariable Integer id) {
		if (servicio.getElement(id) != null) {
			Element elemento = servicio.getElement(id);
			servicio.remove(elemento);
			return elemento;
		} else {
			return null;
		}
	}

}
