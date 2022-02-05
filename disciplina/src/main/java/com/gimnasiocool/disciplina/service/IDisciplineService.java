package com.gimnasiocool.disciplina.service;

import com.gimnasiocool.disciplina.entities.Discipline;

import java.util.List;

public interface IDisciplineService {

    public List<Discipline> findAllDisc();

    public Discipline getDisciplina(Long id);

    public Discipline createDisciplina(Discipline discipline);

    public Discipline updateDisciplina(Discipline discipline);

    public Discipline deleteDisciplina(Long id);

    public Discipline findByName(String name);
}
