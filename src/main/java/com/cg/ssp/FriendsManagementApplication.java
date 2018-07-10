package com.cg.ssp;

/**
 * @author Amit Bhalerao
 * Spring Boot Application to start the application
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FriendsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendsManagementApplication.class, args);
	}
}
