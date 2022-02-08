package com.gimnasiocool.client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gimnasiocool.client.entities.Client;

public interface IClientRepository extends JpaRepository<Client, Long> {

	public List<Client> findByApellido(String apellido);
	
	public Client findByDni(int dni);
}
