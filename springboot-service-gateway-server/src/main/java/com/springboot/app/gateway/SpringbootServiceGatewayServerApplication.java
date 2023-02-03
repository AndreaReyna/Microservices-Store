package com.springboot.app.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringbootServiceGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceGatewayServerApplication.class, args);
	}

}
