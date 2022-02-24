package com.gimnasiocool.discipline.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tbl_disciplinas")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Discipline {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "name", nullable = false)
    @NotEmpty(message = "Debe ingresar un nombre")
    private String nombre;
	
	@Column(name = "description", nullable = false, length = 120)
    private String descripcion;
	
	@Column(name = "status")
    private String status;

    
}
