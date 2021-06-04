package com.example.springboot;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long userId;
    @NotBlank(message = "Username is mandatory")
    public String username;
    @NotBlank(message = "Password is mandatory")
    public String password;
    @NotBlank(message = "Email is mandatory")
    public String email;
    public Date creationTimestamp;
    public Date updateTimestamp;
    public int isDeleted;

    protected User() {}

    public User(String username, String password, String email) {
    	this.username = username;
    	this.password = password;
    	this.email = email;
    	this.creationTimestamp = new Date();
    	this.updateTimestamp = null;
    	this.isDeleted = 0;    	
    }
}
