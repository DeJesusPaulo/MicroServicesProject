package com.gimnasiocool.discipline.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_disciplinas")
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Discipline {

    private Long id;
    private String nombre;
    private String descripcion;
    private String status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "name", nullable = false)
    public String getNombre() {
        return nombre;
    }

    @Column(name = "description")
    public String getDescripcion() {
        return descripcion;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }
}
