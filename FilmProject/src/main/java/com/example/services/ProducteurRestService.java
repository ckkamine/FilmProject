package com.example.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entities.Proposition;
import com.example.metier.IProducteurMetier;


@Controller
@RequestMapping("/rest/producteur")
public class ProducteurRestService {

	@Autowired
	IProducteurMetier producteurMetier;

	@RequestMapping(value="/proposition", method=RequestMethod.POST)
	public @ResponseBody Proposition addProposition(@RequestBody Proposition proposition) throws IOException {
		return producteurMetier.addProposition(proposition);
	}

	@RequestMapping(value="/propositions", method=RequestMethod.GET)
	public @ResponseBody Page<Proposition> getAllPropositions(@RequestParam Integer page, @RequestParam Long idProducteur) {
		return producteurMetier.getAllPropositions(page, idProducteur);
	}
	
	
}
