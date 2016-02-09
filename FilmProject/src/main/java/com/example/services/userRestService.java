package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Utilisateur;
import com.example.repositories.IUtilisateurRepository;

@RestController
@RequestMapping("/rest")
public class userRestService {

	@Autowired
	IUtilisateurRepository utilisateurRepository;
	
	@RequestMapping("/user")
	public Utilisateur user(@RequestParam String username){
		Utilisateur user= utilisateurRepository.findByUsername(username);
		return user;
	}
}
