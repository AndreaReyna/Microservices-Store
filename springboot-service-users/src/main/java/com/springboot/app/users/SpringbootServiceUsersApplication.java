package com.springboot.app.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.springboot.app.commons.users.models"})
public class SpringbootServiceUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceUsersApplication.class, args);
	}

}
