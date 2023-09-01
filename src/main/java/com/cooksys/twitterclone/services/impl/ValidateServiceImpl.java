package com.cooksys.twitterclone.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.entities.User;
import com.cooksys.twitterclone.repositories.UserRepository;
import com.cooksys.twitterclone.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

	private final UserRepository userRepository;

	// checks if username is available and returns false if it it taken
	@Override
	public boolean availableUsername(String username) {

		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getCredentials().getUsername() == username) {
				return false;
			}
		}

		return true;

	}

	// checks if username exists if it does then return true;
	@Override
	public boolean usernameExists(String username) {
		List<User> users = userRepository.findAll();
		for(User user : users) {
			if(user.getCredentials().getUsername() == username) {
				return true;
			}
		}
		
		return false;
	
	}
}
