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
@RequestMapping("/disciplines")
public class DisciplineController {

    @Autowired
	IDisciplineService ds;
    
    @GetMapping
    public ResponseEntity<List<Discipline>> listado(){
    	List<Discipline> disc = ds.findAllDisc();
    	
    	if (disc.isEmpty()) { return ResponseEntity.noContent().build();}
    	
		return ResponseEntity.ok(disc) ;
    }
    
    @GetMapping("{/id}")
    public ResponseEntity<Discipline> buscarPorID(@PathVariable("id") Long id){
    	Discipline disc = ds.getDisciplina(id);
    	
    	if (id == null) { return ResponseEntity.notFound().build(); }
    	
    	return ResponseEntity.ok(disc);
    }
    
    @PostMapping
    public ResponseEntity<Discipline> crearDisciplina(@Valid @RequestBody Discipline discipline, BindingResult result){
    	if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatoMensajes(result));
		}
    	
    	Discipline disc = ds.createDisciplina(discipline);
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(disc);
    }
    
    @PutMapping
    public ResponseEntity<Discipline> actualizarDisciplina(@PathVariable("id") Long id, @RequestBody Discipline discipline){
    	discipline.setId(id);
    	Discipline disc = ds.updateDisciplina(discipline);
    	
    	if (disc == null) { return ResponseEntity.notFound().build(); }
    	
    	
    	return ResponseEntity.ok(disc);	 	
    }
    
    @DeleteMapping
    public ResponseEntity<Discipline> borrarDisciplina(@PathVariable ("id") Long id){
    	Discipline disc = ds.deleteDisciplina(id);
    	
    	if (id == null) { return ResponseEntity.notFound().build(); }
    	
    	return ResponseEntity.ok(disc);
    }
    
    @GetMapping("/nombre")
    public ResponseEntity<Discipline> buscarPorNombre(String nombre){
    	Discipline disc = ds.findByName(nombre);
    	
    	if (nombre == null) { return ResponseEntity.notFound().build(); }
    	
    	
    	return ResponseEntity.ok(disc);
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
