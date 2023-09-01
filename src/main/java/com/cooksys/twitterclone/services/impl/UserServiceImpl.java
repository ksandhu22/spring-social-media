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
	    if(newUser.getCredentials() == null || newUser.getProfile() == null || newUser.getCredentials().getUsername() == null || newUser.getCredentials().getPassword() == null || newUser.getProfile().getEmail() == null) {
	        throw new BadRequestException("Necessary fields must not be empty");
	    }
	    User hopefullyFound = userRepository.findByUsername(newUser.getCredentials().getUsername());
	    if(hopefullyFound != null && !hopefullyFound.isDeleted()) {
	        throw new BadRequestException("This username already exists and is not deactivated");
	    }
	    if(hopefullyFound != null && hopefullyFound.isDeleted()) {
	        hopefullyFound.setDeleted(false);
	        return userMapper.entityToDto(userRepository.saveAndFlush(hopefullyFound));
	    }
	    User addedUser = userMapper.requestDtoToEntity(newUser);
	    return userMapper.entityToDto( userRepository.saveAndFlush(addedUser));
	}

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

//	@Override
//	public UserResponseDto updateUsername(String username, CredentialsDto credentials) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<UserResponseDto> getFollowing(String user) {
		User userToFollow = userRepository.findByUsername(user);
		if (userToFollow == null || userToFollow.isDeleted()) {
			throw new NotFoundException("User does not exist");
		}

		return userMapper.entitiesToDtos(userToFollow.getFollowing());
	}

	@Override
	public List<UserResponseDto> getFollowers(String user) {
		User userFollowingList = userRepository.findByUsername(user);
		if (userFollowingList == null || userFollowingList.isDeleted()) {
			throw new NotFoundException("User does not exist");
		}

		return userMapper.entitiesToDtos(userFollowingList.getFollowing());
	}
    
}
