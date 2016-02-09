package com.example.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.Utilisateur;

public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	
	public Utilisateur findByUsername(String username);

}
