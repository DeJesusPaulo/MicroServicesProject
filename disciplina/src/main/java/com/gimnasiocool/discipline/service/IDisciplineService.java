package com.gimnasiocool.discipline.service;

import java.util.List;

import com.gimnasiocool.discipline.entities.Discipline;

public interface IDisciplineService {

    public List<Discipline> findAllDisc();

    public Discipline getDisciplina(Long id);

    public Discipline createDisciplina(Discipline discipline);

    public Discipline updateDisciplina(Discipline discipline);

    public Discipline deleteDisciplina(Long id);

    public Discipline findByName(String name);
}
