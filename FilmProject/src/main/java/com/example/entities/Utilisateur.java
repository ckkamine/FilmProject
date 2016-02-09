package com.example.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Utilisateur implements Serializable, UserDetails {
	
	public static final String UTILISATEUR = "USER";
	public static final String ADMINISTRATEUR = "ADMIN";
	public static final String PRODUCTEUR = "PRODUCTEUR";
	public static final String GERANT = "GERANT";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUtilisateur;
	@NotNull
	private String username;
	@NotNull
	private String password;
	private String nom;
	private String prenom;
	private String email;
	private Integer telephone;
	private String adresse;
	@NotNull
	private String role;
	
	
	public Utilisateur(){}
	
	

	public Utilisateur(String username, String password, String nom, String prenom, String role) {
		super();
		this.username = username;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
	}



	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}

	
	
	public Integer getTelephone() {
		return telephone;
	}

	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}

	

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.getRole()==null){
			return Collections.emptyList();
		}
			Set<GrantedAuthority> authorities= new HashSet<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(role(this.getRole())));
			return authorities;
		
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		
		return this.password;
	}

	@Override
	public String getUsername() {
		
		return this.username;
	}
	
	public String role(String role){
		return "ROLE_"+role;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
