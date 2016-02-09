package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.entities.Proposition;
import com.example.entities.Salle;
import com.example.metier.IGerantMetier;

@RestController
@RequestMapping("/rest/gerant")
public class GerantRestService {

	@Autowired
	IGerantMetier gerantMetier;

	@RequestMapping(value="/propositions", method=RequestMethod.GET)
	public Page<Proposition> getAllPropositions(@RequestParam Integer page) {
		return gerantMetier.getAllPropositions(page);
	}


	@RequestMapping(value="/affecter", method=RequestMethod.PUT)
	public void affecterFilm(@RequestBody Salle salle, @RequestParam Integer creneau, @RequestParam Integer prix, @RequestParam Long idProposition) {
		gerantMetier.affecterFilm(salle, creneau, prix, idProposition);
	}

	@RequestMapping(value="/annuler", method=RequestMethod.PUT)
	public void annulerProposition(@RequestParam Long idProposition) {
		gerantMetier.annulerProposition(idProposition);
	}
	
	
}
