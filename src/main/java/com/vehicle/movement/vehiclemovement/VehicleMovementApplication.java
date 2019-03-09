package com.vehicle.movement.vehiclemovement;

//import com.vehicle.movement.vehiclemovement.controller.VehicleThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VehicleMovementApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(VehicleMovementApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(VehicleMovementApplication.class);
	}
}

