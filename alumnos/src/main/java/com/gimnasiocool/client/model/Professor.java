package com.gimnasiocool.client.model;

import lombok.Data;

@Data
public class Professor {
	
	private Long id;
	private String nombre;
	private String apellido;
	private int dni;
	private String email;
	private String status;

}
