package com.gimnasiocool.client.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gimnasiocool.client.model.Professor;

@FeignClient(name = "profesores-service")
@RequestMapping("/profesores")
public interface ProfessorClient {
    @GetMapping("{/id}")
    public ResponseEntity<Professor> buscarPorID(@PathVariable("id") Long id);

}
