package com.gimnasiocool.alumno.client;

import com.gimnasiocool.alumno.model.Discipline;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "disciplinas-service")
@RequestMapping("/disciplinas")
public interface DisciplineClient {
    @GetMapping("{/id}")
    public ResponseEntity<Discipline> buscarPorID(@PathVariable("id") Long id);
}
