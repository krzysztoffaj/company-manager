package com.krzysztoffaj.companymanager;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableJpaAuditing
public class CompanyManagerApplication {

	public static void main(String[] args) {
		run(CompanyManagerApplication.class, args);
	}
}
