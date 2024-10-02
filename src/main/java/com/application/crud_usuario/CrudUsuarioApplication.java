package com.application.crud_usuario;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
	public class CrudUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudUsuarioApplication.class, args);
	}

}
