package com.gimnasiocool.alumno.controller;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class MensajeError {
	
	private String code;
	private List<Map<String, String>> mensajes;

}
