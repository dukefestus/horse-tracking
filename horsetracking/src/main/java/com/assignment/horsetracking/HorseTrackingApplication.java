package com.assignment.horsetracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HorseTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HorseTrackingApplication.class, args);
		HorseTracking.startHorseTracking();
	}

}
