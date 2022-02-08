package com.gimnasiocool.client.service;

import java.util.List;

import com.gimnasiocool.client.entities.Client;

public interface IClientService {
	
	public List<Client> findAllClients();
	
	public Client getClient(Long id);
	
	public Client createClient(Client client);
	
	public Client updateClient(Client client);
	
	public Client deleteClient(Long id);
	
	public List<Client> searchBySurname(String surname);

	Client searchByDni(int dni);

}
