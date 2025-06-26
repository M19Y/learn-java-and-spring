package com.m19y.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync // untuk mengaktifkan fitur async, annotatsi ini harus di buat di main class
@EnableScheduling // untuk mengaktifkan sceduling, annotatis ini busa di simpan pada config atau main class
public class BelajarSpringAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelajarSpringAsyncApplication.class, args);
	}

}
