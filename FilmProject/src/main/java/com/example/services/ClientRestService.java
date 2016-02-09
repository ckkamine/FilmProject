package com.example.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Projection;
import com.example.entities.Reservation;
import com.example.entities.Salle;
import com.example.metier.IClientMetier;

@RestController
@RequestMapping("/rest/client")
public class ClientRestService {
	
	@Autowired
	IClientMetier clientMetier;

	@Secured(value="ROLE_CLIENT")
	@RequestMapping(value="/reserver", method= RequestMethod.POST)
	public Reservation reserverUneProjection(@RequestBody Reservation reservation) {
		return clientMetier.reserverUneProjection(reservation);
	}

	@Secured(value="ROLE_CLIENT")
	@RequestMapping(value="/annuler", method= RequestMethod.PUT)
	public void annulerReservation(@RequestParam Long idReservation) {
		clientMetier.annulerReservation(idReservation);
	}

	@RequestMapping(value="/projections", method= RequestMethod.GET)
	public List<Projection> getAllProjectionsByDateAndSalle(@RequestParam Long idSalle, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
		return clientMetier.getAllProjectionsByDateAndSalle(idSalle, date);
	}

	@RequestMapping(value="/salles", method= RequestMethod.GET)
	public List<Salle> getAllSalles() {
		return clientMetier.getAllSalles();
	}

	@Secured(value="ROLE_CLIENT")
	@RequestMapping(value="/reservations", method= RequestMethod.GET)
	public Page<Reservation> findByIdUtilisateur(@RequestParam Long idClient, @RequestParam Integer page) {
		return clientMetier.findByIdUtilisateur(idClient, page);
	}
	
	
	 

}
