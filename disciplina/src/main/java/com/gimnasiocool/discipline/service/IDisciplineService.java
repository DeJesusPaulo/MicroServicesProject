package com.gimnasiocool.discipline.service;

import java.util.List;

import com.gimnasiocool.discipline.entities.Discipline;

public interface IDisciplineService {

    public List<Discipline> findAllDisciplines();

    public Discipline getDiscipline(Long id);

    public Discipline createDiscipline(Discipline discipline);

    public Discipline updateDiscipline(Discipline discipline);

    public Discipline deleteDiscipline(Long id);

    public Discipline findByName(String name);
}
