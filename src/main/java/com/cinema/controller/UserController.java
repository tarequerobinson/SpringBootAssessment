package com.cinema.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.entities.User;
import com.cinema.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;

	UserController (UserService userService ) {
		this.userService = userService;
		
	}
	

    @GetMapping ("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
    	List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
	
    }
    
	
	
	
	
	

}
