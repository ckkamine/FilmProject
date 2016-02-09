package com.example.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Projection implements Serializable{
	
	public static final int creneau_1= 1;
	public static final int creneau_2= 2;
	public static final int creneau_3= 3;
	public static final int creneau_4= 4;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProjection;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="FILM")
	private Film film;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_SALLE")
	private Salle salle;
	private Date date;
	private Integer prix;
	private Integer creneau;
	
	public Projection(){
		
	}
	
	public Projection(Film film, Salle salle, Date date, Integer prix, Integer creneau){
		this.film= film;
		this.salle= salle;
		this.creneau= creneau;
		this.prix= prix;
		this.date= date;
	}

	public Long getIdProjection() {
		return idProjection;
	}

	public void setIdProjection(Long idProjection) {
		this.idProjection = idProjection;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getPrix() {
		return prix;
	}

	public void setPrix(Integer prix) {
		this.prix = prix;
	}

	public Integer getCreneau() {
		return creneau;
	}

	public void setCreneau(Integer creneau) {
		this.creneau = creneau;
	}
	
	
}
