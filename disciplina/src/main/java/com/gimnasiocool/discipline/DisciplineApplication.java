package com.gimnasiocool.discipline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DisciplineApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisciplineApplication.class, args);
	}

}
