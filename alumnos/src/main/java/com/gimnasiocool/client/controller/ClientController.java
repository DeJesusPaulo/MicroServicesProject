	package com.gimnasiocool.client.controller;

	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.stream.Collectors;

	import javax.validation.Valid;

import com.gimnasiocool.client.entities.Client;
import com.gimnasiocool.client.service.IClientService;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.validation.BindingResult;
	import org.springframework.web.bind.annotation.*;
	import org.springframework.web.server.ResponseStatusException;

	import com.fasterxml.jackson.core.JsonProcessingException;
	import com.fasterxml.jackson.databind.ObjectMapper;

	@RestController
	@RequestMapping("/api/v1/clients")
	public class ClientController {

		@Autowired
		IClientService clientService;

		public ResponseEntity<List<Client>> getAllClients(){
			List<Client> clients = clientService.findAllClients();

			if (clients.isEmpty()) { return ResponseEntity.noContent().build();}

			return ResponseEntity.ok(clients);
		}


		@GetMapping("/search/{id}")
		public ResponseEntity<Client> getClientById(@PathVariable ("id") Long id){
			Client client = clientService.getClient(id);
			if (id == null) { return ResponseEntity.notFound().build();}

			return ResponseEntity.ok(client);
		}


		@PostMapping
		public ResponseEntity<Client> createClient(@Valid @RequestBody Client client, BindingResult result){
			if (result.hasErrors()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatoMensajes(result));
			}
			Client client1 = clientService.createClient(client);

			return ResponseEntity.status(HttpStatus.CREATED).body(client1);
		}


		@PutMapping({"/update/{id}"})
		public ResponseEntity<Client> updateClient(@PathVariable ("id") Long id, @RequestBody Client client){
			client.setId(id);
			Client client1 = clientService.updateClient(client);
			if (client1 == null) { return ResponseEntity.notFound().build(); }

			return ResponseEntity.ok(client1);
		}

		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Client> deleteClient(Long id){
			Client client = clientService.deleteClient(id);
			if (client == null) { return ResponseEntity.notFound().build(); }

			return ResponseEntity.ok(client);
		}

		@GetMapping("/searchbysurname/{surname}")
		public ResponseEntity<List<Client>> findBySurname(@PathVariable ("surname") String surname){
			List<Client> clients = new ArrayList<>();
			clients = clientService.searchBySurname(surname);

			if (clients == null) { return ResponseEntity.notFound().build(); }

			return ResponseEntity.ok(clients);
		}

		@GetMapping("/searchbydni/{dni}")
		public ResponseEntity<Client>  findByDni(@PathVariable ("dni") int dni){
			Client client = clientService.searchByDni(dni);

			if (client == null) { return ResponseEntity.notFound().build(); }

			return ResponseEntity.ok(client);
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
