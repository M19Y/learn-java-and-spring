package com.m19y.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // <- this is for reads all the servlet component (cause spring by default dosen't read web servlet)
public class  BelajarSpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelajarSpringMvcApplication.class, args);
	}

}
