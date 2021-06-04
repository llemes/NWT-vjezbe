package com.example.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	@Bean
	CommandLineRunner initDatabase(UserRepository repository) {

		return args -> {

			repository.save(new User("user1", "pass1", "11111@email.ba"));
			repository.save(new User("user2", "pass2", "22222@email.ba"));
			repository.save(new User("user3", "pass3", "33333@email.ba"));
			repository.save(new User("user4", "pass4", "44444@email.ba"));
			repository.save(new User("user5", "pass5", "55555@email.ba"));

		};
	}
}