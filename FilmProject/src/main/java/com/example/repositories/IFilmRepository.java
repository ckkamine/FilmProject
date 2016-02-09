package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Film;

public interface IFilmRepository extends JpaRepository<Film, Long> {

}
