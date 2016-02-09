package com.example.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.Projection;

public interface IProjectionRepository extends JpaRepository<Projection, Long> {

	@Query("select p from Projection p where p.date >= :x and p.salle.idSalle = :s and p.creneau= :c")
	public List<Projection> getFuturesProjections(@Param("x") Date date, @Param("s") Long idSalle, @Param("c") Integer creneau);
	
	@Query("select p from Projection p where p.date >= :x and p.salle.idSalle = :s ")
	public List<Projection> getFuturesProjections(@Param("x") Date date, @Param("s") Long idSalle);
	
	@Query("select p from Projection p where p.date = :x and p.salle.idSalle = :s and p.creneau= :c")
	public List<Projection> getFuturesProjectionByDay(@Param("x") Date date, @Param("s") Long idSalle, @Param("c") Integer creneau);
	
	@Query("select p from Projection p where p.date = :x and p.salle.idSalle = :s ")
	public List<Projection> getProjectionsByDateAndSalle(@Param("x") Date date, @Param("s") Long idSalle);
}
