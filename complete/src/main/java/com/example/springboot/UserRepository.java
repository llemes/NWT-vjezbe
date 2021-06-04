package com.example.springboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM USER WHERE username = ?1", nativeQuery = true)
	User getByUsername(String username);

}
