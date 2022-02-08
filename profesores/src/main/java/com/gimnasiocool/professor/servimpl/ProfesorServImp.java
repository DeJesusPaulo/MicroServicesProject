package com.gimnasiocool.professor.servimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gimnasiocool.professor.entities.Professor;
import com.gimnasiocool.professor.repository.I_ProfesorRepository;
import com.gimnasiocool.professor.service.I_ProfesorService;

@Service
public class ProfesorServImp implements I_ProfesorService {
	
	@Autowired
	I_ProfesorRepository professorRepo;

	@Override
	public List<Professor> findAllProfesor() {
		
		return professorRepo.findAllProf();
	}

	@Override
	public Professor getProfesor(Long id) {
		
		return professorRepo.findById(id).orElse(null);
	}

	@Override
	public Professor createProfesor(Professor professor) {
		Professor prof = getProfesor(professor.getId());
		if (prof == null) { return null; }
		
		prof.setStatus("Profesor creado");
				
		return professorRepo.save(prof);
	}

	@Override
	public Professor updateProfesor(Professor professor) {
		Professor prof = getProfesor(professor.getId());
		if (prof== null ) { return null; }
		
		prof.setNombre(professor.getNombre());
		prof.setApellido(professor.getApellido());
		prof.setDni(professor.getDni());
		prof.setEmail(professor.getEmail());
		prof.setStatus("Profesor actualizado");
		
		return professorRepo.save(prof);
	}

	@Override
	public Professor deleteProfesor(Long id) {
		Professor prof = getProfesor(id);
		if (prof== null ) { return null; }
		
		prof.setStatus("Profesor Borrado");
		
		return professorRepo.save(prof);
	}

	@Override
	public List<Professor> searchByApellido(String apellido) {
		
		return professorRepo.findByApellido(apellido);
	}

}
