package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Film;
import com.example.entities.Projection;
import com.example.entities.Proposition;
import com.example.entities.Reservation;
import com.example.entities.Salle;
import com.example.entities.Utilisateur;
import com.example.metier.IAdministrateurMetier;

@RestController
@RequestMapping("/rest/admin")
public class AdministrateurRestService {
	
	@Autowired
	IAdministrateurMetier administrateurMetier;

	@RequestMapping(value="/film", method= RequestMethod.DELETE)
	public void deleteFilm(@RequestParam Long idFilm) {
		administrateurMetier.deleteFilm(idFilm);
	}

	@RequestMapping(value="/utilisateur", method= RequestMethod.DELETE)
	public void deleteUser(@RequestParam Long idUtilisateur) {
		administrateurMetier.deleteUser(idUtilisateur);
	}

	@RequestMapping(value="/proposition", method= RequestMethod.DELETE)
	public void deleteProposition(@RequestParam Long idProposition) {
		administrateurMetier.deleteProposition(idProposition);
	}

	@RequestMapping(value="/reservation", method= RequestMethod.DELETE)
	public void deleteReservation(@RequestParam Long idReservation) {
		administrateurMetier.deleteReservation(idReservation);
	}

	@RequestMapping(value="/projection", method= RequestMethod.DELETE)
	public void deleteProjection(@RequestParam Long idProjection) {
		administrateurMetier.deleteProjection(idProjection);
	}

	@RequestMapping(value="/gerant", method= RequestMethod.POST)
	public Utilisateur addGerant(@RequestBody Utilisateur utilisateur) {
		return administrateurMetier.addGerant(utilisateur);
	}

	@RequestMapping(value="/salle", method= RequestMethod.POST)
	public Salle addSalle(@RequestBody Salle salle) {
		return administrateurMetier.addSalle(salle);
	}

	@RequestMapping(value="/utilisateurs", method= RequestMethod.GET)
	public Page<Utilisateur> getAllUsers(@RequestParam Integer page) {
		return administrateurMetier.getAllUsers(page);
	}

	@RequestMapping(value="/films", method= RequestMethod.GET)
	public Page<Film> getAllFilms(@RequestParam Integer page) {
		return administrateurMetier.getAllFilms(page);
	}

	@RequestMapping(value="/propositions", method= RequestMethod.GET)
	public Page<Proposition> getAllProposition(@RequestParam Integer page) {
		return administrateurMetier.getAllProposition(page);
	}

	@RequestMapping(value="/reservations", method= RequestMethod.GET)
	public Page<Reservation> getAllReservations(@RequestParam Integer page) {
		return administrateurMetier.getAllReservations(page);
	}

	@RequestMapping(value="/projections", method= RequestMethod.GET)
	public Page<Projection> getAllProjections(@RequestParam Integer page) {
		return administrateurMetier.getAllProjections(page);
	}
	
	
	
}
