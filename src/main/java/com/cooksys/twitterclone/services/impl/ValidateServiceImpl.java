package com.cooksys.twitterclone.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.entities.Hashtag;
import com.cooksys.twitterclone.entities.Tweet;
import com.cooksys.twitterclone.entities.User;
import com.cooksys.twitterclone.repositories.HashtagRepository;
import com.cooksys.twitterclone.repositories.UserRepository;
import com.cooksys.twitterclone.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

	private final UserRepository userRepository;
	private final HashtagRepository hashtagRepository;

	// checks if username is available and returns false if it it taken
	@Override
	public boolean availableUsername(String username) {

		List<User> users = userRepository.findAll();
		Optional<User> usernameAvailable = userRepository.findByCredentialsUsername(username);
		if (usernameAvailable.isEmpty()) {
			return true;
		}
		for (User user : users) {
			if (user.getCredentials().getUsername() == usernameAvailable.get().getCredentials().getUsername()) {
				return false;
			}
		}

		return true;		

	}

	// checks if username exists if it does then return true;
	@Override
	public boolean usernameExists(String username) {
		List<User> users = userRepository.findAll();
		Optional<User> usernameExists = userRepository.findByCredentialsUsername(username);
		if (usernameExists.isEmpty()) {
			return false;
		}
		for(User user : users) {
			if(user.getCredentials().getUsername() == usernameExists.get().getCredentials().getUsername()) {
				return true;
			}
		}
		
		return false;
	
	}

	//Does not pass test because for loop does not return true so they are not equal.
	//Need to check values 
	@Override
	public boolean hashtagExists(String label) {
		List<Hashtag> hashtags = hashtagRepository.findAll();
		Optional<Hashtag> hashtagExists = hashtagRepository.findByLabel(label);
		if (hashtags.isEmpty()) {
			return false;
		}
		//trying to read print statement to see incoming values maybe missing '#' to make hashtag equal
		for(Hashtag tag : hashtags) {
			System.out.println(tag.getLabel());
			System.out.println(hashtagExists.get().getLabel());
			if(tag.getLabel().contentEquals(hashtagExists.get().getLabel())) {
				return true;
			}
		}
		
		return false;
	}
}
