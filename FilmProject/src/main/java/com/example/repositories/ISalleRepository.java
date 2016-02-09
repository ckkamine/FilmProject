package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Salle;

public interface ISalleRepository extends JpaRepository<Salle, Long> {

}
