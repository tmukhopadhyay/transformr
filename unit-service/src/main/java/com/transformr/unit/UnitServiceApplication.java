package com.transformr.unit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import com.transformr.commons.CommonConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
@Import(CommonConfiguration.class)
public class UnitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnitServiceApplication.class, args);
	}
}
