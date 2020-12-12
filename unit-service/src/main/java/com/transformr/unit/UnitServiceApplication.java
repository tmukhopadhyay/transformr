package com.transformr.unit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UnitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnitServiceApplication.class, args);
	}
}
