package com.gimnasiocool.client.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.gimnasiocool.client.model.Discipline;
import com.gimnasiocool.client.model.Professor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "tbl_alumnos")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	@NotEmpty(message = "Debe ingresar un nombre")
	private String name;
	
	@Column(name = "surname", nullable = false)
	@NotEmpty(message = "Debe ingresar un apellido")
	private String surname;
	
	@Column(name = "DNI",nullable = false,unique = true,length = 12)
	private int dni;
	
	@Column(name = "email")
	@Email(message = "Forma incorrecta")
	private String email;

	@Column(name = "status", nullable = false)
	private String status;
	
	@Transient
	private Discipline discipline;
	
	@Transient
	private Professor professor;
	

}
