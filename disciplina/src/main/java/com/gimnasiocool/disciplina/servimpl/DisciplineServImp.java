package com.gimnasiocool.disciplina.servimpl;

import com.gimnasiocool.disciplina.entities.Discipline;
import com.gimnasiocool.disciplina.repository.IDisciplineRepository;
import com.gimnasiocool.disciplina.service.IDisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineServImp implements IDisciplineService {

    @Autowired
    IDisciplineRepository dr;

    @Override
    public List<Discipline> findAllDisc() {
        return dr.findAll();
    }

    @Override
    public Discipline getDisciplina(Long id) {

        return dr.findById(id).orElse(null);
    }

    @Override
    public Discipline createDisciplina(Discipline discipline) {
        Discipline disc =getDisciplina(discipline.getId());
        
        if (disc == null) { ResponseEntity.notFound().build(); }
        
        disc.setStatus("Disciplina Creada");
        

        return dr.save(disc);
    }

    @Override
    public Discipline updateDisciplina(Discipline discipline) {
    	Discipline disc =getDisciplina(discipline.getId());
    	if (disc == null) { ResponseEntity.notFound().build(); }
    	
    	disc.setNombre(discipline.getNombre());
    	disc.setDescripcion(discipline.getDescripcion());
    	disc.setStatus("Disciplina actualizada");
    	  
    	  
    	
        return dr.save(disc);
    }

    @Override
    public Discipline deleteDisciplina(Long id) {
    	Discipline disc = getDisciplina(id);
    	if (disc == null ) { return null; }
    	
    	disc.setStatus("Disciplina borrada");
			
    	
        return dr.save(disc);
    }

    @Override
    public Discipline findByName(String name) {
    	
    	
        return dr.findByNombre(name);
    }
}
