package com.gimnasiocool.client.servimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gimnasiocool.client.clients.DisciplineClient;
import com.gimnasiocool.client.clients.ProfessorClient;
import com.gimnasiocool.client.entities.Client;
import com.gimnasiocool.client.model.Discipline;
import com.gimnasiocool.client.model.Professor;
import com.gimnasiocool.client.repository.IClientRepository;
import com.gimnasiocool.client.service.IClientService;

@Service
public class ClientServImp implements IClientService {

	@Autowired
	IClientRepository repo;
	@Autowired
	DisciplineClient disciplineClient;
	@Autowired
	ProfessorClient professorClient;

	@Override
	public List<Client> findAllClients() {

		return repo.findAll();
	}

	@Override
	public Client getClient(Long id) {

		return repo.findById(id).orElse(null);
	}

	@Override
	public Client createClient(Client client) {
		Client client1 = getClient(client.getId());
		if (client != null) {

			client.setStatus("Client created");

		}

		return repo.save(client1);
	}

	@Override
	public Client updateClient(Client client) {
		Client client1 = getClient(client.getId());
		if (client != null) {

			client.setName(client.getName());
			client.setSurname(client.getSurname());
			client.setDni(client.getDni());
			client.setEmail(client.getEmail());
			Discipline disc = disciplineClient.buscarPorID(client.getId()).getBody();
			client.setDiscipline(disc);
			Professor prof = professorClient.buscarPorID(client.getId()).getBody();
			client.setProfessor(prof);
			client.setStatus("Client updated");

		}

		return repo.save(client1);
	}

	@Override
	public Client deleteClient(Long id) {
		Client client = getClient(id);
		if (client != null) {
		
			client.setStatus("Client deleted");
		}

		return repo.save(client);
	}

	@Override
	public List<Client> searchBySurname(String surname) {

		return repo.findByApellido(surname);
	}

	@Override
	public Client searchByDni(int dni) {

		return repo.findByDni(dni);
	}
}
