package com.example.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.Reservation;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
	
	@Query("select count(*) from Reservation r where r.projection.salle.idSalle= :i and r.projection.creneau= :c and r.projection.date= :d")
	public Long nombreDeReservation(@Param("i") Long idSalle,@Param("c") Integer creneau, @Param("d") Date date);
	
	@Query("select r from Reservation r where r.client.idUtilisateur = :x")
	public Page<Reservation> findByIdUtilisateur(@Param("x") Long idClient, Pageable page);

}
