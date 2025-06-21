package com.productrial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ProductTrialApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductTrialApplication.class, args);
	}

}
