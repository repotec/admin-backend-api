package com.ntg.adm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class AdmServicesApplication {	
	public static void main(String[] args) {
		SpringApplication.run(AdmServicesApplication.class, args);
	}
}
