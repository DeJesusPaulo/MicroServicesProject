package com.gimnasiocool.disciplina.repository;

import com.gimnasiocool.disciplina.entities.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDisciplineRepository extends JpaRepository<Discipline,Long> {

    public Discipline findByNombre(String nombre);

}
