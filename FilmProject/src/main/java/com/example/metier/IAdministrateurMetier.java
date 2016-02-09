package com.example.metier;

import java.util.Collection;

import org.springframework.data.domain.Page;

import com.example.entities.Film;
import com.example.entities.Projection;
import com.example.entities.Proposition;
import com.example.entities.Reservation;
import com.example.entities.Salle;
import com.example.entities.Utilisateur;

public interface IAdministrateurMetier {
	
	public void deleteFilm(Long idFilm);
	public void deleteUser(Long idUtilisateur);
	public void deleteProposition(Long idProposition);
	public void deleteReservation(Long idReservation);
	public void deleteProjection(Long idProjection);
	public Utilisateur addGerant(Utilisateur utilisateur);
	public Salle addSalle(Salle salle);
	public Page<Utilisateur> getAllUsers(Integer page);
	public Page<Film> getAllFilms(Integer page);
	public Page<Proposition> getAllProposition(Integer page);
	public Page<Reservation> getAllReservations(Integer page);
	public Page<Projection> getAllProjections(Integer page);

}
