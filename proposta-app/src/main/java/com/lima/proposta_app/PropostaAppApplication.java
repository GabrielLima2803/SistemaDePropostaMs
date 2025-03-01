package com.lima.proposta_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PropostaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaAppApplication.class, args);
	}

}
