package com.krzysztoffaj.companymanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class CompanyManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyManagerApplication.class, args);
	}

}