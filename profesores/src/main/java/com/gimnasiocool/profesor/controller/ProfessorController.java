package com.gimnasiocool.profesor.controller;

import com.gimnasiocool.professor.entities.Professor;
import com.gimnasiocool.professor.service.I_ProfesorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfessorController {

	@Autowired
	I_ProfesorService professorService;

	@GetMapping
	public ResponseEntity<List<Professor>> listadoProfs() {
		List<Professor> listado = professorService.findAllProfesor();

		if (listado == null) { return ResponseEntity.noContent().build();}

		return ResponseEntity.ok(listado);
	}

	@GetMapping("{/id}")
	public ResponseEntity<Professor> findById(@PathVariable ("id") Long id){
		Professor profe = professorService.getProfesor(id);

		if (id == null) { return ResponseEntity.notFound().build(); }

		return ResponseEntity.ok(profe);
	}

	@PostMapping
	public ResponseEntity<Professor> createProfessor(Professor professor){
		Professor profe = professorService.createProfesor(professor);

		return ResponseEntity.ok(profe);
	}

	@PutMapping
	public ResponseEntity<Professor> updateProfessor(@RequestBody Professor professor){
		Professor profe = professorService.updateProfesor(professor);

		if (profe == null) {return ResponseEntity.noContent().build();}

		return ResponseEntity.ok(profe);
	}
	
	@DeleteMapping
	public ResponseEntity<Professor> deleteProfessor(@PathVariable ("id") Long id){
		Professor profe = professorService.deleteProfesor(id);
		
		if (id == null) { return ResponseEntity.notFound().build(); }
			
		
		return ResponseEntity.ok(profe);
	}

	@GetMapping("/busqueda/{apellido}")
	public ResponseEntity<List<Professor>> findByApellido(String apellido){
		List<Professor> profes = professorService.searchByApellido(apellido);
		
		if (profes.isEmpty()) { return ResponseEntity.noContent().build(); }
		
		return ResponseEntity.ok(profes);
	}
}
