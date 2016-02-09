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
public class Reservation implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idReservation;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_CLIENT")
	private Utilisateur client;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PROJECTION")
	private Projection projection;
	private Date dateReservation;
	
	public Reservation(){
		
	}

	public Reservation(Date date, Utilisateur client, Projection projection){
		this.client= client;
		this.projection= projection;
		this.dateReservation= date;
	}

	public Long getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Long idReservation) {
		this.idReservation = idReservation;
	}

	public Utilisateur getClient() {
		return client;
	}

	public void setClient(Utilisateur client) {
		this.client = client;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}
	
	
	
}
