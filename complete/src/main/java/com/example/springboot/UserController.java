package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.NotFoundException;

import java.rmi.NoSuchObjectException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping(value = "", produces={"application/json"})
	public ResponseMessage getAll() {
		ResponseMessage response = new ResponseMessage();
		
		response.Response = userRepository.findAll();
		response.Succeeded = true;
		
		return response;
	}

	@GetMapping(value = "/{id}", produces={"application/json"})
	public ResponseMessage getUserById(@PathVariable(value = "id") Long id) throws NoSuchObjectException, NotFoundException {
		ResponseMessage response = new ResponseMessage();
		
		response.Response = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User with given id not found"));
		response.Succeeded = true;
		
		return response;
	}

	@PostMapping(value = "", produces={"application/json"})
	public ResponseMessage createUser(@RequestBody User user) {
		ResponseMessage response = new ResponseMessage();
		
		response.Response = userRepository.save(user);
		response.Succeeded = true;
		
		return response;
	}

	@DeleteMapping(value = "/{id}", produces={"application/json"})
	public ResponseMessage deleteUser(@PathVariable(value = "id") Long id) throws NoSuchObjectException, NotFoundException {

		ResponseMessage response = new ResponseMessage();
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User with given id not found"));

		userRepository.delete(user);
		
		response.Response = ResponseEntity.ok().build();
		response.Succeeded = true;
		
		return response;
	}
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleNotFoundException(
			NotFoundException exception
	) throws JsonProcessingException {
		ResponseMessage response = new ResponseMessage();
		
		response.Succeeded = false;
		response.Message =  exception.getMessage();
		
		String serialized = new ObjectMapper().writeValueAsString(response);
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		
		return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .headers(headers)
        .body(serialized);
	}

	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String>  handleConstraintViolationException(
			javax.validation.ConstraintViolationException exception
	) throws JsonProcessingException {
		ResponseMessage response = new ResponseMessage();
		
		response.Succeeded = false;
		response.Message = exception.getConstraintViolations().iterator().next().getMessage();
		
		String serialized = new ObjectMapper().writeValueAsString(response);
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		
		return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .headers(headers)
        .body(serialized);
	}

}
