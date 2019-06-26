package com.krzysztoffaj.companymanager;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        ErrorMvcAutoConfiguration.class
})
public class CompanyManagerApplication {

	public static void main(String[] args) {
		run(CompanyManagerApplication.class, args);
	}
}
