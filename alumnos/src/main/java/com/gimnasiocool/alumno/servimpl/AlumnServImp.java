package com.gimnasiocool.alumno.servimpl;

import java.util.List;

import com.gimnasiocool.alumno.client.DisciplineClient;
import com.gimnasiocool.alumno.client.ProfessorClient;
import com.gimnasiocool.alumno.entities.Alumn;
import com.gimnasiocool.alumno.model.Discipline;
import com.gimnasiocool.alumno.model.Professor;
import com.gimnasiocool.alumno.service.IAlumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gimnasiocool.alumno.repository.IAlumnRepository;

@Service
public class AlumnServImp implements IAlumnService {

	@Autowired
	IAlumnRepository repo;
	@Autowired
	DisciplineClient disciplineClient;
	@Autowired
	ProfessorClient professorClient;

	@Override
	public List<Alumn> findAllAlumnos() {

		return repo.findAll();
	}

	@Override
	public Alumn getAlumno(Long id) {

		return repo.findById(id).orElse(null);
	}

	@Override
	public Alumn createAlumno(Alumn alumn) {
		Alumn alum = getAlumno(alumn.getId());
		if (alum != null) {

			alum.setStatus("Alumno creado");

		}

		return repo.save(alum);
	}

	@Override
	public Alumn updateAlumno(Alumn alumn) {
		Alumn alum = getAlumno(alumn.getId());
		if (alum != null) {

			alum.setName(alumn.getName());
			alum.setSurname(alumn.getSurname());
			alum.setDni(alumn.getDni());
			alum.setEmail(alumn.getEmail());
			Discipline disc = disciplineClient.buscarPorID(alumn.getId()).getBody();
			alum.setDiscipline(disc);
			Professor prof = professorClient.buscarPorID(alumn.getId()).getBody();
			alum.setProfessor(prof);
			alum.setStatus("Alumno actualizado");

		}

		return repo.save(alum);
	}

	@Override
	public Alumn deleteAlumno(Long id) {
		Alumn alum = getAlumno(id);
		if (alum != null) {
		
		alum.setStatus("Alumno Borrado");
		}

		return repo.save(alum);
	}

	@Override
	public List<Alumn> searchByApellido(String apellido) {

		return repo.findByApellido(apellido);
	}

	@Override
	public Alumn searchByDni(int dni) {

		return repo.findByDni(dni);
	}
}
