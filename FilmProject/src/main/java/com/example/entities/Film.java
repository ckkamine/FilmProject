package com.example.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Film implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idFilm;
	private String titre;
	private String resume;
	private Integer annee;
	private String genre;
	private String producteur;
	@ElementCollection
	private Collection<String> acteurs;
	@Lob
	private byte[] image;
	
	public Film(){}
	
	

	public Film(String titre, String resume, Integer annee, String genre, String producteur, List<String> acteurs,
			byte[] image) {
		super();
		this.titre = titre;
		this.resume = resume;
		this.annee = annee;
		this.genre = genre;
		this.producteur = producteur;
		this.acteurs = acteurs;
		this.image = image;
	}



	public Long getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(Long idFilm) {
		this.idFilm = idFilm;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getProducteur() {
		return producteur;
	}

	public void setProducteur(String producteur) {
		this.producteur = producteur;
	}

	public Collection<String> getActeurs() {
		return acteurs;
	}

	public void setActeurs(List<String> acteurs) {
		this.acteurs = acteurs;
	}

	@JsonIgnore
	public byte[] getImage() {
		return image;
	}

	@JsonSetter
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	

}
