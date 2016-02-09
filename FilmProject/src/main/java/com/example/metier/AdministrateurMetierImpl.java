package com.example.metier;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.Film;
import com.example.entities.Projection;
import com.example.entities.Proposition;
import com.example.entities.Reservation;
import com.example.entities.Salle;
import com.example.entities.Utilisateur;
import com.example.repositories.IFilmRepository;
import com.example.repositories.IProjectionRepository;
import com.example.repositories.IPropositionRepository;
import com.example.repositories.IReservationRepository;
import com.example.repositories.ISalleRepository;
import com.example.repositories.IUtilisateurRepository;

@Service
@Transactional
public class AdministrateurMetierImpl implements IAdministrateurMetier{

	@Autowired
	IFilmRepository filmRepository;
	
	@Autowired
	IProjectionRepository projectionRepository;
	
	@Autowired
	IPropositionRepository propositionRepository;
	
	@Autowired
	IReservationRepository reservationRepository;
	
	@Autowired
	ISalleRepository salleRepository;
	
	@Autowired
	IUtilisateurRepository utilisateurRepository;
	
	
	@Override
	public void deleteFilm(Long idFilm) {
		filmRepository.delete(idFilm);
	}

	@Override
	public void deleteUser(Long idUtilisateur) {
		utilisateurRepository.delete(idUtilisateur);
	}

	@Override
	public void deleteProposition(Long idProposition) {
		propositionRepository.delete(idProposition);
	}

	@Override
	public void deleteReservation(Long idReservation) {
		reservationRepository.delete(idReservation);
	}

	@Override
	public void deleteProjection(Long idProjection) {
		projectionRepository.delete(idProjection);
	}

	@Override
	public Utilisateur addGerant(Utilisateur utilisateur) {
		utilisateur.setRole(Utilisateur.GERANT);
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public Salle addSalle(Salle salle) {
		return salleRepository.save(salle); 
	}

	@Override
	public Page<Utilisateur> getAllUsers(Integer page) {
		return utilisateurRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public Page<Film> getAllFilms(Integer page) {
		return filmRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public Page<Proposition> getAllProposition(Integer page) {
		return propositionRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public Page<Reservation> getAllReservations(Integer page) {
		return reservationRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public Page<Projection> getAllProjections(Integer page) {
		return projectionRepository.findAll(new PageRequest(page, 10));
	}

}
