package com.gimnasiocool.alumno.service;

import java.util.List;

import com.gimnasiocool.alumno.entities.Alumn;

public interface IAlumnService {
	
	public List<Alumn> findAllAlumnos();
	
	public Alumn getAlumno(Long id);
	
	public Alumn createAlumno(Alumn alumn);
	
	public Alumn updateAlumno(Alumn alumn);
	
	public Alumn deleteAlumno(Long id);
	
	public List<Alumn> searchByApellido(String apellido);

	Alumn searchByDni(int dni);

}
