package com.example.metier;



import org.springframework.data.domain.Page;


import com.example.entities.Proposition;
import com.example.entities.Salle;

public interface IGerantMetier {
	
	public Page<Proposition> getAllPropositions(Integer page);
	
	public void affecterFilm(Salle salle, Integer creneau, Integer prix, Long idProposition);
	public void annulerProposition(Long idProposition); 
	
}
