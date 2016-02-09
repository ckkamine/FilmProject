package com.example.metier;

import org.springframework.data.domain.Page;

import com.example.entities.Proposition;

public interface IProducteurMetier {

	public Proposition addProposition(Proposition proposition);
	public Page<Proposition> getAllPropositions(Integer page, Long idGerant);
}
