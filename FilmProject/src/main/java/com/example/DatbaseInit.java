package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.entities.Film;
import com.example.entities.Proposition;
import com.example.entities.Utilisateur;
import com.example.repositories.IFilmRepository;
import com.example.repositories.IPropositionRepository;
import com.example.repositories.IUtilisateurRepository;

@Component
public class DatbaseInit {
	
	@Autowired
	IUtilisateurRepository utilisateurRepository;
	
	@Autowired
	IPropositionRepository propositionRepository;
	
	@Autowired
	IFilmRepository filmRepository;
	
	




	public void init(){
		Utilisateur gerant= new Utilisateur("gerant", "495e1001389cb2641848c7f2ac222530", "Yahya", "Harif", Utilisateur.GERANT);
		Utilisateur producteur= new Utilisateur("producteur", "60bdde299d4c253ba87617c8f8174ed9", "Amine", "Choukoukou", Utilisateur.PRODUCTEUR);
		Utilisateur client= new Utilisateur("client", "62608e08adc29a8d6dbc9754e659f125", "Ali", "Meddoune", Utilisateur.UTILISATEUR);
		utilisateurRepository.save(gerant);
		utilisateurRepository.save(producteur);
		utilisateurRepository.save(client);
		List<String> acteurs = new ArrayList<>();
		for(int i=0; i<3 ; i++){
			acteurs.add("Acteur "+i);
		}
		Film film = new Film("Titanic", "resume", 2006, "Romantique", "Producteur", acteurs, null);
		filmRepository.save(film);
		Proposition proposition= new Proposition(film, new Date(116, 1, 13), 2, 14, producteur);
		propositionRepository.save(proposition);
		
	}

}
