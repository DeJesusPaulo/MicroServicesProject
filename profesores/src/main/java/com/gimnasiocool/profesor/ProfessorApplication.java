package com.gimnasiocool.profesor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProfessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfessorApplication.class, args);
	}

}
