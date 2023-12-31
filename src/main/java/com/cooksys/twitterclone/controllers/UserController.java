package com.cooksys.twitterclone.controllers;

import java.util.List;

import com.cooksys.twitterclone.exceptions.BadRequestException;

import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@PatchMapping("/@{username}")
	  public UserResponseDto updateUsername(@PathVariable String username, @RequestBody UserRequestDto user) {
		return userService.updateUsername(username, user);
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
	public List<UserResponseDto> getFollowing(@PathVariable String username){
		return userService.getFollowing(username);
	}
	
	@GetMapping("/@{username}/followers")
	public List<UserResponseDto> getFollowers(@PathVariable String username){
		return userService.getFollowers(username);
	}

	@PostMapping("/@{username}/follow")
	public void followUser(@PathVariable String username, @RequestBody CredentialsDto user) {
		 userService.followUser(username, user);
	}

	@PostMapping("/@{username}/unfollow")
	public void unfollowUser(@PathVariable String username, @RequestBody CredentialsDto user) {
		userService.unfollowUser(username, user);
	}
	
	@DeleteMapping("/@{username}")
	public UserResponseDto deleteUser(@PathVariable String username) {
		return userService.deleteUser(username);
	}
	
	@GetMapping("/@{username}/feed")
	public List<TweetResponseDto> getFeed(@PathVariable String username){
		return userService.getFeed(username);
	}
	
	@GetMapping("/@{username}/mentions")
	public List<TweetResponseDto> getMentions(@PathVariable String username){
		return userService.getMentions(username);
	}
	

}
