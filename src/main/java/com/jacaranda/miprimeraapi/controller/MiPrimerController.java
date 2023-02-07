package com.jacaranda.miprimeraapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiPrimerController {
	@GetMapping("/hola")
	public String getHola() {
		return "hola";
	}

}
