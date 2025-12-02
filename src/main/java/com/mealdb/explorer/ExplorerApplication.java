package com.mealdb.explorer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.mealdb",
	    "com.mealdb.config",     
	    "com.mealdb.service",   
	    "com.mealdb.controller" ,
	    "com.mealdb.model"
	})
public class ExplorerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExplorerApplication.class, args);
	}

}
