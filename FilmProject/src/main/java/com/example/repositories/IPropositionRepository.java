package com.example.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.Proposition;

public interface IPropositionRepository extends JpaRepository<Proposition, Long> {
	
	@Query("select p from Proposition p where p.producteur.idUtilisateur =:x ")
	public Page<Proposition> getAllPrpositions(@Param("x") Long idUtilisateur, Pageable page);

}
