package com.cooksys.twitterclone.controllers;

import com.cooksys.twitterclone.dtos.UserResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cooksys.twitterclone.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	
	 private final UserService userService;

	 @GetMapping
	 public List<UserResponseDto> getAllUsers() {
		 return userService.getAllUsers();
	 }

	@PostMapping
	public UserResponseDto addUser(@RequestBody UserResponseDto newUser) {
		 return userService.addUser(newUser);
	}
}
