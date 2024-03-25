package com.EatEasy;

import com.EatEasy.Services.InitialDataCreationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EatEasyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatEasyApplication.class, args);
	}
	@Bean
	public CommandLineRunner init(InitialDataCreationService service) {
		return args -> {
			service.createFakerOwner(6);
			service.createFakerUser(25);
			service.createFakerRestaurant(12);
			service.createFakerAdmin(5);
			service.createFakerBooking(10);
			service.createFakerFavoriteRestaurant(8);
			service.createFakerImage(15);
			service.createFakerReview(20);
		};
	}
}
