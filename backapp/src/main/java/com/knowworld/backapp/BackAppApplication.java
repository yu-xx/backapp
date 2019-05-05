package com.knowworld.backapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class BackAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackAppApplication.class, args);
	}
}
