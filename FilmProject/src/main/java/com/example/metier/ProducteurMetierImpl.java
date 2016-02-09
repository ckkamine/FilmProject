package com.example.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.Proposition;
import com.example.repositories.IPropositionRepository;

@Service
@Transactional
public class ProducteurMetierImpl implements IProducteurMetier {

	@Autowired
	IPropositionRepository propositionRepository;
	
	@Override
	public Proposition addProposition(Proposition proposition) {
		proposition.setStatus(Proposition.ENVOYE);
		return propositionRepository.save(proposition);
	}

	@Override
	public Page<Proposition> getAllPropositions(Integer page, Long idGerant) {
		
		return propositionRepository.getAllPrpositions(idGerant, new PageRequest(page, 10));
	}

}
