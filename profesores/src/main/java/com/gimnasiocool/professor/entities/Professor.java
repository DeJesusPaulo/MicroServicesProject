package com.gimnasiocool.professor.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_profesores")
public class Professor implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	@NotEmpty(message = "Debe ingresar un nombre")
	private String nombre;
	
	@Column(name = "surname", nullable = false)
	@NotEmpty(message = "Debe ingresar un nombre")
	private String apellido;
	
	@Column(name = "DNI",nullable = false,unique = true,length = 12)
	@Size(min = 12,max = 12,message = "Cantidad de numeros incorrecta")
	@NotEmpty(message = "Debe ingresar numero de documento")
	private int dni;
	
	@Column(name = "email")
	@Email(message = "Forma incorrecta")
	private String email;
	
	@Column(name = "status", nullable = false)
	private String status;
	

}
