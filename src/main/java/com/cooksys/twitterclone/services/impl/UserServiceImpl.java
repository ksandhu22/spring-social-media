package com.cooksys.twitterclone.services.impl;

import com.cooksys.twitterclone.dtos.CredentialsDto;
import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.dtos.UserRequestDto;
import com.cooksys.twitterclone.dtos.UserResponseDto;
import com.cooksys.twitterclone.entities.Tweet;
import com.cooksys.twitterclone.entities.User;
import com.cooksys.twitterclone.exceptions.BadRequestException;
import com.cooksys.twitterclone.exceptions.NotAuthorizedException;
import com.cooksys.twitterclone.exceptions.NotFoundException;
import com.cooksys.twitterclone.mappers.CredentialsMapper;
import com.cooksys.twitterclone.mappers.ProfileMapper;
import com.cooksys.twitterclone.mappers.TweetMapper;
import com.cooksys.twitterclone.mappers.UserMapper;
import com.cooksys.twitterclone.repositories.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.services.UserService;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final ProfileMapper profileMapper;
	private final CredentialsMapper credentialsMapper;
	private final TweetMapper tweetMapper;

	@Override
	public List<UserResponseDto> getAllUsers() {

		return userMapper.entitiesToDtos(userRepository.findAll().stream().filter(user -> !user.isDeleted()).toList());
	}

	@Override
	public UserResponseDto addUser(UserRequestDto newUser) {
		Optional<User> addedUser = userRepository.findByUsername(newUser.getCredentials().getUsername());

		// User addedUser = userMapper.requestDtoToEntity(newUser);
		if (newUser.getCredentials().getUsername() == null || newUser.getCredentials().getPassword() == null
				|| newUser.getProfile().getEmail() == null) {
			throw new BadRequestException("Necessary fields must not be empty");
		}

		if (!addedUser.get().isDeleted() && addedUser != null) {
			throw new BadRequestException("This username already exists and is not deactivated");
		}

		if (addedUser.get().isDeleted() && addedUser != null) {
			addedUser.get().setDeleted(false);
			return userMapper.entityToDto(userRepository.saveAndFlush(addedUser.get()));
		}

		userRepository.saveAndFlush(addedUser.get());
		return userMapper.entityToDto(addedUser.get());

	}

//    	for(User user : userRepository.findAll()) {
//    	    if(user.getCredentials().getUsername() == addedUser.getCredentials().getUsername() && user.isDeleted()) {
//    	        addedUser.setDeleted(false);
//    	        return userMapper.entityToDto(addedUser);
//    	    }
//    	    if(user.getCredentials().getUsername() == addedUser.getCredentials().getUsername() && !user.isDeleted()) {
//    	        throw new BadRequestException("This username already exists and is not deactivated");
//    	    }
//    	}
//    	userRepository.saveAndFlush(addedUser);
//    	return userMapper.entityToDto(addedUser);

//	@Override
//	public boolean validateUsername() {
//		// TODO Auto-generated method stub
//		return false;
//	}

//	@Override
//	public UserResponseDto updateUsername(String username, CredentialsDto credentials) {
//		User user = userRepository.findByUsername(username);
//		if(!userRepository.findAll().contains(user) || user.isDeleted()) {
//			throw new NotFoundException("User not found");
//		}
//		
//		if(!user.getCredentials().equals(credentialsMapper.credentialsDtoToEntity(credentials))) {
//			throw new NotAuthorizedException("User not found");
//		}
//		
//		user.getCredentials().setUsername(username);
//		return userMapper.entityToResponseDto(user);
//		
//		
//	}

	@Override
	public UserResponseDto getUser(String username) {
		Optional<User> findUser = userRepository.findByCredentials(username);
		if (findUser.isEmpty()) {
			throw new NotFoundException("User is not found");

		}

		User user = findUser.get();

		// Return user not list
		return userMapper.entityToDto(user);
	}

	@Override
	public List<TweetResponseDto> getTweets(String username) {
		Optional<User> findUser = userRepository.findByCredentials(username);
		if (findUser.isEmpty()) {
			throw new NotFoundException("User is not found");

		}

		User user = findUser.get();
		List<Tweet> userTweets = new ArrayList<>(user.getTweets());

		return tweetMapper.entitiesToResponseDtos(userTweets);
	}

	@Override
	public UserResponseDto updateUsername(String username, CredentialsDto credentials) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserResponseDto> getFollowing(String user) {
		User user = userRepository.findByUsername(user);
		if (user == null || user.isDeleted()) {
			throw new NotFoundException("User does not exist");
		}

		return userMapper.entitiesToDtos(user.getFollowing());
	}

	@Override
	public List<UserResponseDto> getFollowers(String user) {
		User user = userRepository.findByUsername(user);
		if (user == null || user.isDeleted()) {
			throw new NotFoundException("User does not exist");
		}

		return userMapper.entitiesToDtos(user.getFollowing());
	}

}
