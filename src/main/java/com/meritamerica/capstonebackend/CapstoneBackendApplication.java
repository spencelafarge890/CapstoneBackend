package com.meritamerica.capstonebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.meritamerica.capstonebackend.repositories.AccountHolderRepository;

@SpringBootApplication
public class CapstoneBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneBackendApplication.class, args);
	}

}
