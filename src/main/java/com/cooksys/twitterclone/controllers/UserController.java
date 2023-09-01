package com.cooksys.twitterclone.controllers;

import java.util.List;

import com.cooksys.twitterclone.exceptions.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.twitterclone.dtos.CredentialsDto;
import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.dtos.UserRequestDto;
import com.cooksys.twitterclone.dtos.UserResponseDto;
import com.cooksys.twitterclone.services.UserService;

import lombok.RequiredArgsConstructor;

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
	public UserResponseDto addUser(@RequestBody UserRequestDto newUser) {
		return userService.addUser(newUser);
	}
	
	@PatchMapping("/@{newName}")
	  public UserResponseDto updateUsername(@PathVariable String username,@PathVariable CredentialsDto credentials) {
		return userService.updateUsername(username, credentials); 
	  }
	
//	@GetMapping
//	public boolean validateUsername() {
//		return userService.validateUsername();
//	}
	
	@GetMapping("/@{username}")
	public UserResponseDto getUser(@PathVariable String username){
		return userService.getUser(username);
	}
	
	@GetMapping("/@{username}/tweets")
	public List<TweetResponseDto> getTweets(@PathVariable String username){
		return userService.getTweets(username);
	}
	
	@GetMapping("/@{username}/following")
	public List<UserResponseDto> getFollowing(@PathVariable String user){
		return userService.getFollowing(user);
	}
	
	@GetMapping("/@{username}/followers")
	public List<UserResponseDto> getFollowers(@PathVariable String user){
		return userService.getFollowers(user);
	}
	
	
	
	
}
