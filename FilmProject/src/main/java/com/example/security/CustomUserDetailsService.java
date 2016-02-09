package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import com.example.entities.Utilisateur;
import com.example.repositories.IUtilisateurRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IUtilisateurRepository utilisateurRepository;



	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur user= utilisateurRepository.findByUsername(username);
		if(user==null){
			//System.out.println("L'utilisateur n'existe pas !");
			throw new UsernameNotFoundException("L'utilisateur n'existe pas "+username);
		}
		return user;
	}


	

}
