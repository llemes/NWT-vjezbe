package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/weather")
public class WeatherController {
	
	@GetMapping(value = "/{query}", produces={"application/json"})
	public ResponseMessage getUserById(@PathVariable(value = "query") String query) throws JsonProcessingException {
		ResponseMessage response = new ResponseMessage();
		
		final String uri = "http://api.weatherapi.com/v1/current.json?key=a4877b32a4eb4e818ac130220210406&q=" + query;
	    RestTemplate restTemplate = new RestTemplate();
		
	    String jsonString = restTemplate.getForObject(uri, String.class);
	    
	    Object serialized = new Gson().fromJson(jsonString, Object.class);
	    
		response.Response = serialized;
		response.Succeeded = true;
		
		return response;
	}
}
