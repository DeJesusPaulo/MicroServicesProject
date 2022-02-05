package com.gimnasiocool.profesor.repository;

import java.util.List;

import com.gimnasiocool.profesor.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface I_ProfesorRepository extends JpaRepository<Professor, Long> {
	
	public List<Professor> findAllProf();
	
	public List<Professor> findByApellido(String apellido);

}
