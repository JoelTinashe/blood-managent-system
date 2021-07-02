package com.digitalkrapht.bloodbank.bloodbank;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@OpenAPIDefinition(info = @Info(title = "Blood Bank System"))
@SpringBootApplication
public class BloodbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodbankApplication.class, args);
	}

}
