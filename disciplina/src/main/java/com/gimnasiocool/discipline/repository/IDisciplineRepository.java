package com.gimnasiocool.discipline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gimnasiocool.discipline.entities.Discipline;

public interface IDisciplineRepository extends JpaRepository<Discipline,Long> {

    public Discipline findByNombre(String nombre);

}
