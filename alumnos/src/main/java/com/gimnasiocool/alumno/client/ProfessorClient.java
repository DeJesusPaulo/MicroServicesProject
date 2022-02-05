package com.gimnasiocool.alumno.client;

import com.gimnasiocool.alumno.model.Professor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "profesores-service")
@RequestMapping("/profesores")
public interface ProfessorClient {
    @GetMapping("{/id}")
    public ResponseEntity<Professor> buscarPorID(@PathVariable("id") Long id);

}
