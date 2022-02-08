package com.gimnasiocool.client.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gimnasiocool.client.model.Discipline;

@FeignClient(name = "disciplinas-service")
@RequestMapping("/disciplinas")
public interface DisciplineClient {
    @GetMapping("{/id}")
    public ResponseEntity<Discipline> buscarPorID(@PathVariable("id") Long id);
}
