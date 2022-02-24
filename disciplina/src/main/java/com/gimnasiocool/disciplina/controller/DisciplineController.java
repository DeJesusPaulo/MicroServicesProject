package com.gimnasiocool.disciplina.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gimnasiocool.discipline.entities.Discipline;
import com.gimnasiocool.discipline.service.IDisciplineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/disciplines")
public class DisciplineController {

    @Autowired
	IDisciplineService disciplineService;
    
    @GetMapping
    public ResponseEntity<List<Discipline>> listado(){
    	List<Discipline> disciplines = disciplineService.findAllDisciplines();
    	
    	if (disciplines.isEmpty()) { return ResponseEntity.noContent().build();}
    	
		return ResponseEntity.ok(disciplines) ;
    }
    
    @GetMapping("/search/{id}")
    public ResponseEntity<Discipline> buscarPorID(@PathVariable("id") Long id){
    	Discipline discipline1 = disciplineService.getDiscipline(id);
    	
    	if (id == null) { return ResponseEntity.notFound().build(); }
    	
    	return ResponseEntity.ok(discipline1);
    }
    
    @PostMapping("/create")
    public ResponseEntity<Discipline> createDiscipline(@Valid @RequestBody Discipline discipline, BindingResult result){
    	if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatoMensajes(result));
		}
    	
    	Discipline discipline1 = disciplineService.createDiscipline(discipline);
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(discipline1);
    }
    
    @PutMapping("/update")
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable("id") Long id, @RequestBody Discipline discipline){
    	discipline.setId(id);
    	Discipline discipline1 = disciplineService.updateDiscipline(discipline);
    	
    	if (discipline1 == null) { return ResponseEntity.notFound().build(); }
    	
    	
    	return ResponseEntity.ok(discipline1);	 	
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Discipline> borrarDisciplina(@PathVariable ("id") Long id){
    	Discipline discipline1 = disciplineService.deleteDiscipline(id);
    	
    	if (id == null) { return ResponseEntity.notFound().build(); }
    	
    	return ResponseEntity.ok(discipline1);
    }
    
    @GetMapping("/searchbyname/nombre")
    public ResponseEntity<Discipline> findByName(String name){
    	Discipline discipline1 = disciplineService.findByName(name);
    	
    	if (name == null) { return ResponseEntity.notFound().build(); }
    	
    	
    	return ResponseEntity.ok(discipline1);
    }
    
    
    private String formatoMensajes(BindingResult result) {
    	List<Map<String , String>> errores = result.getFieldErrors().stream()
    			.map(err ->{ Map<String , String> error= new HashMap<>();
    			error.put(err.getCode(), err.getDefaultMessage());
    			return error;	
    			}).collect(Collectors.toList());
    	
    	MensajeError mensajeError = MensajeError.builder()
    			.code("01")
    			.mensajes(errores)
    			.build();
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonString = "";
    	try {
			jsonString = mapper.writeValueAsString(mensajeError);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	
    	return jsonString;
    }

   
}
