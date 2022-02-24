package com.gimnasiocool.discipline.servimpl;

import com.gimnasiocool.discipline.entities.Discipline;
import com.gimnasiocool.discipline.repository.IDisciplineRepository;
import com.gimnasiocool.discipline.service.IDisciplineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineServImp implements IDisciplineService {

    @Autowired
    IDisciplineRepository disciplineRespository;

    @Override
    public List<Discipline> findAllDisciplines() {
        return disciplineRespository.findAll();
    }

    @Override
    public Discipline getDiscipline(Long id) {

        return disciplineRespository.findById(id).orElse(null);
    }

    @Override
    public Discipline createDiscipline(Discipline discipline) {
        Discipline discipline1 =getDiscipline(discipline.getId());
        
        if (discipline1 == null) { ResponseEntity.notFound().build(); }
        
        discipline1.setStatus("Discipline created");
        

        return disciplineRespository.save(discipline1);
    }

    @Override
    public Discipline updateDiscipline(Discipline discipline) {
    	Discipline discipline1 =getDiscipline(discipline.getId());
    	if (discipline1 == null) { ResponseEntity.notFound().build(); }
    	
    	discipline1.setNombre(discipline.getNombre());
    	discipline1.setDescripcion(discipline.getDescripcion());
    	discipline1.setStatus("Discipline updated");
    	  
        return disciplineRespository.save(discipline1);
    }

    @Override
    public Discipline deleteDiscipline(Long id) {
    	Discipline discipline1 = getDiscipline(id);
    	if (discipline1 == null ) { return null; }
    	
    	discipline1.setStatus("Disciplina borrada");
			
    	
        return disciplineRespository.save(discipline1);
    }

    @Override
    public Discipline findByName(String name) {
    	
    	
        return disciplineRespository.findByName(name);
    }
}
