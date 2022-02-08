package com.gimnasiocool.professor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gimnasiocool.professor.entities.Professor;

public interface I_ProfesorRepository extends JpaRepository<Professor, Long> {
	
	public List<Professor> findAllProf();
	
	public List<Professor> findByApellido(String apellido);

}
