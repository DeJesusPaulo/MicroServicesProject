package com.gimnasiocool.alumno.repository;

import java.util.List;

import com.gimnasiocool.alumno.entities.Alumn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlumnRepository extends JpaRepository<Alumn, Long> {

	public List<Alumn> findByApellido(String apellido);
	
	public Alumn findByDni(int dni);
}
