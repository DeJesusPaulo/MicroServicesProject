package com.gimnasiocool.profesor.service;

import java.util.List;

import com.gimnasiocool.profesor.entities.Professor;

public interface I_ProfesorService {
	
	public List<Professor> findAllProfesor();
	
	public Professor getProfesor(Long id);
	
	public Professor createProfesor(Professor professor);
	
	public Professor updateProfesor(Professor professor);
	
	public Professor deleteProfesor(Long id);
	
	public List<Professor> searchByApellido(String apellido);

}
