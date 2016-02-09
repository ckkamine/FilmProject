package com.example.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Proposition implements Serializable {
	
	public static final String ANNULE="Annulée";
	public static final String ENVOYE= "Envoyée";
	public static final String ACCEPTE= "Acceptée";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProposition;
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	private Film film;
	private Date dateDebut;
	private String status;
	private boolean envoye;
	private Integer nombreDeSemaines;
	private Integer nombreDeJours;
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="ID_PRODUCTEUR")
	private Utilisateur producteur;
	public Proposition() {
		super();
		
	}
	public Proposition(Film film, Date dateDebut, Integer nombreDeSemaines, Integer nombreDeJours,
			Utilisateur producteur) {
		super();
		this.film = film;
		this.dateDebut = dateDebut;
		this.nombreDeSemaines = nombreDeSemaines;
		this.nombreDeJours = nombreDeJours;
		this.producteur = producteur;
	}
	public Long getIdProposition() {
		return idProposition;
	}
	public void setIdProposition(Long idProposition) {
		this.idProposition = idProposition;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Integer getNombreDeSemaines() {
		return nombreDeSemaines;
	}
	public void setNombreDeSemaines(Integer nombreDeSemaines) {
		this.nombreDeSemaines = nombreDeSemaines;
	}
	public Integer getNombreDeJours() {
		return nombreDeJours;
	}
	public void setNombreDeJours(Integer nombreDeJours) {
		this.nombreDeJours = nombreDeJours;
	}
	public Utilisateur getProducteur() {
		return producteur;
	}
	public void setProducteur(Utilisateur producteur) {
		this.producteur = producteur;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isEnvoye() {
		return envoye;
	}
	public void setEnvoye(boolean envoye) {
		this.envoye = envoye;
	}
	
	
}
