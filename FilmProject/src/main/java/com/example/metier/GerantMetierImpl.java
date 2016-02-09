package com.example.metier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.entities.Projection;
import com.example.entities.Proposition;
import com.example.entities.Salle;

import com.example.repositories.IProjectionRepository;
import com.example.repositories.IPropositionRepository;
import com.example.repositories.IUtilisateurRepository;

@Service
@Transactional
public class GerantMetierImpl implements IGerantMetier{
	
	
	@Autowired
	IPropositionRepository propositionRepository;
	
	@Autowired
	IProjectionRepository projectionRepository;
	
	@Autowired
	IUtilisateurRepository utilisateurRepository;

	@Override
	public Page<Proposition> getAllPropositions(Integer page) {
		return propositionRepository.findAll(new PageRequest(page, 10));
	}

	

	@Override
	public void affecterFilm(Salle salle, Integer creneau, Integer prix, Long idProposition) {
		List<Integer> creneaux= new ArrayList<Integer>();
		for(int j=1;j<5;j++){
			if(!(j==creneau)){
				creneaux.add(j);
				
			}
		}
		Calendar calendar= new GregorianCalendar();
		Proposition proposition= propositionRepository.findOne(idProposition);
		calendar.setTime(proposition.getDateDebut());
		List<Projection> projections= projectionRepository.getFuturesProjections(proposition.getDateDebut(), salle.getIdSalle(), creneau);
		for(int i=0; i < proposition.getNombreDeJours();i++){
			
			
			boolean exist = false;
			for(Projection p: projections){
				System.out.println("p: "+p.getIdProjection());
				if(
						(p.getDate().getDate()==calendar.getTime().getDate())
						&&
						(p.getDate().getMonth()==calendar.getTime().getMonth())
						){
					exist=true;
					break;
				}
			}
			if(!exist){
				Projection projection= new Projection(proposition.getFilm(), salle, calendar.getTime(), prix, creneau);
				projectionRepository.save(projection);
			}else{
				
				for(Integer c:creneaux){
					exist=false;
					System.out.println("creneau: "+c);
					List<Projection> projectionsByDay= projectionRepository.getFuturesProjectionByDay(calendar.getTime(), salle.getIdSalle(), c);
					for(Projection p: projectionsByDay){
						System.out.println("pByDay: "+p.getIdProjection());
						if(
								(p.getDate().getDate()==calendar.getTime().getDate())
								&&
								(p.getDate().getMonth()==calendar.getTime().getMonth())
								){
							exist=true;
							break;
						}
					}
					if(!exist){
						Projection projection= new Projection(proposition.getFilm(), salle, calendar.getTime(), prix, c);
						projectionRepository.save(projection);
						break;
					}
				}
			}
			calendar.add(Calendar.DATE, 1);
		}
		proposition.setStatus(Proposition.ACCEPTE);
		propositionRepository.save(proposition);
	}

	@Override
	public void annulerProposition(Long idProposition) {
		Proposition proposition = propositionRepository.findOne(idProposition);
		proposition.setStatus(Proposition.ANNULE);
		propositionRepository.save(proposition);
	}
	

}
