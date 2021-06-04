package com.example.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;
	
	@Test
	public void should_find_all_users() {
		
		repository.save(new User("user1", "pass1", "11111@email.ba"));
		repository.save(new User("user2", "pass2", "22222@email.ba"));
		repository.save(new User("user3", "pass3", "33333@email.ba"));
		repository.save(new User("user4", "pass4", "44444@email.ba"));
		repository.save(new User("user5", "pass5", "55555@email.ba"));
		
		List<User> users = repository.findAll();
		
		assertThat(users).hasSize(5);
	}
	

	@Test
	public void should_find_users_by_id() {
		
		Optional<User> user = repository.findById((long) 1);
		
		assertThat(user).isNotEmpty();
		

		Optional<User> user2 = repository.findById((long) 2);
		
		assertThat(user2).isNotEmpty();
		
	}
	
}
