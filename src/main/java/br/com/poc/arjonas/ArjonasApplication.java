package br.com.poc.arjonas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ArjonasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArjonasApplication.class, args);
	}

}
