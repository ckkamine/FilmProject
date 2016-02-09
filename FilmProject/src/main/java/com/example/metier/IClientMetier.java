package com.example.metier;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.entities.Projection;
import com.example.entities.Reservation;
import com.example.entities.Salle;

public interface IClientMetier {
	
	public Reservation reserverUneProjection(Reservation reservation);
	public void annulerReservation(Long idReservation);
	public List<Projection> getAllProjectionsByDateAndSalle(Long idSalle, Date date);
	public List<Salle> getAllSalles();
	public Page<Reservation> findByIdUtilisateur(Long idClient, Integer page);

}
