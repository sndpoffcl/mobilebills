package com.cg.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages= {"com.cg.billing"})
@EntityScan(basePackages="com.cg.billing.beans")
@EnableJpaRepositories(basePackages="com.cg.billing.daoservices")
@EnableWebMvc
public class CgBillingPortalSpringBootMvcjpaDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(CgBillingPortalSpringBootMvcjpaDataApplication.class, args);
	}

}
