	package com.gimnasiocool.alumno.controller;

	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.stream.Collectors;

	import javax.validation.Valid;

	import com.gimnasiocool.alumno.entities.Alumn;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.validation.BindingResult;
	import org.springframework.web.bind.annotation.*;
	import org.springframework.web.server.ResponseStatusException;

	import com.fasterxml.jackson.core.JsonProcessingException;
	import com.fasterxml.jackson.databind.ObjectMapper;
	import com.gimnasiocool.alumno.service.IAlumnService;

	@RestController
	@RequestMapping("/api/v1/alumns")
	public class AlumnController {

		@Autowired
		IAlumnService alumnService;


		public ResponseEntity<List<Alumn>> getAllAlumns(){
			List<Alumn> alumns = alumnService.findAllAlumnos();

			if (alumns.isEmpty()) { return ResponseEntity.noContent().build();}

			return ResponseEntity.ok(alumns);
		}


		@GetMapping({"/id"})
		public ResponseEntity<Alumn> getAlumnById(@PathVariable ("id") Long id){
			Alumn alumn = alumnService.getAlumno(id);
			if (id == null) { return ResponseEntity.notFound().build();}

			return ResponseEntity.ok(alumn);
		}


		@PostMapping
		public ResponseEntity<Alumn> createAlumn(@Valid @RequestBody Alumn alumn, BindingResult result){
			if (result.hasErrors()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatoMensajes(result));
			}
			Alumn alum = alumnService.createAlumno(alumn);

			return ResponseEntity.status(HttpStatus.CREATED).body(alum);
		}


		@PutMapping({"/update/id"})
		public ResponseEntity<Alumn> updateAlumn(@PathVariable ("id") Long id, @RequestBody Alumn alumno){
			alumno.setId(id);
			Alumn alumn = alumnService.updateAlumno(alumno);
			if (alumn == null) { return ResponseEntity.notFound().build(); }

			return ResponseEntity.ok(alumn);
		}

		@DeleteMapping
		public ResponseEntity<Alumn> deleteAlumn(Long id){
			Alumn alumn = alumnService.deleteAlumno(id);
			if (alumn == null) { return ResponseEntity.notFound().build(); }

			return ResponseEntity.ok(alumn);
		}

		@GetMapping("/busqueda/{apellido}")
		public ResponseEntity<List<Alumn>> findBySurname(@PathVariable ("apellido") String apellido){
			List<Alumn> alumns = new ArrayList<>();
			alumns = alumnService.searchByApellido(apellido);

			if (alumns == null) { return ResponseEntity.notFound().build(); }

			return ResponseEntity.ok(alumns);
		}

		public ResponseEntity<Alumn>  findByDni(@PathVariable ("dni") int dni){
			Alumn alumn = alumnService.searchByDni(dni);

			if (alumn == null) { return ResponseEntity.notFound().build(); }

			return ResponseEntity.ok(alumn);
		}

		private String formatoMensajes(BindingResult result) {
			List<Map<String, String>> errores = result.getFieldErrors().stream()
					.map(err ->{
						Map<String, String> error = new HashMap<>();
						error.put(err.getField(), err.getDefaultMessage());
						return error;
					} ).collect(Collectors.toList());

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
