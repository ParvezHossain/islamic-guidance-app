package com.parvez.guidance.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GuidanceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuidanceAppApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner init(ApplicationContext ctx) {
//		return args -> {
//			System.out.println("Application has been initialized");
//		};
//	}

}
