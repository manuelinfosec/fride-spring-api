package com.cit305.fride.fride;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cit305.fride.fride")
public class FrideApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrideApplication.class, args);
	}

}
