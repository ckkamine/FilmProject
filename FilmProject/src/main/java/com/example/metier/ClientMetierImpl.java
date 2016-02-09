package com.example.metier;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.Projection;
import com.example.entities.Reservation;
import com.example.entities.Salle;
import com.example.repositories.IProjectionRepository;
import com.example.repositories.IReservationRepository;
import com.example.repositories.ISalleRepository;

@Service
@Transactional
public class ClientMetierImpl implements IClientMetier{

	@Autowired
	IReservationRepository reservationRepository;
	
	@Autowired
	IProjectionRepository projectionRepository;
	
	@Autowired
	ISalleRepository salleRepository;
	
	@Override
	public Reservation reserverUneProjection(Reservation reservation) {
		Projection projection= projectionRepository.findOne(reservation.getProjection().getIdProjection());
		Long nombreDeReservation= reservationRepository.nombreDeReservation(projection.getSalle().getIdSalle(), 
				projection.getCreneau(), projection.getDate());
		if((nombreDeReservation<projection.getSalle().getCapacite())&&(new Date().getTime()<projection.getDate().getTime())){
			return reservationRepository.save(reservation);
		}else{
			return null;
		}
		
	}

	@Override
	public void annulerReservation(Long idReservation) {
		Reservation reservation= reservationRepository.findOne(idReservation);
		if(new Date().getTime() < reservation.getProjection().getDate().getTime()){
			reservationRepository.delete(idReservation);
		}
	}

	@Override
	public List<Projection> getAllProjectionsByDateAndSalle(Long idSalle, Date date) {
		
		return projectionRepository.getProjectionsByDateAndSalle(date, idSalle);
	}

	@Override
	public List<Salle> getAllSalles() {
		return salleRepository.findAll();
	}

	@Override
	public Page<Reservation> findByIdUtilisateur(Long idClient, Integer page) {
		return reservationRepository.findByIdUtilisateur(idClient, new PageRequest(page, 10));
	}

	
}
