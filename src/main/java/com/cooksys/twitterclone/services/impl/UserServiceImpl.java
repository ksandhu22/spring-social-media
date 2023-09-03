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

import javax.swing.text.html.Option;
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
		Optional<User> findUser = userRepository.findByCredentialsUsername(username);
		if (findUser.isEmpty()) {
			throw new NotFoundException("User is not found");

		}

		User user = findUser.get();

		return userMapper.entityToDto(user);
	}

	@Override
	public List<TweetResponseDto> getTweets(String username) {
		Optional<User> findUser = userRepository.findByCredentialsUsername(username);
		if (findUser.isEmpty()) {
			throw new NotFoundException("User is not found");

		}

		User user = findUser.get();
		List<Tweet> userTweets = new ArrayList<>(user.getTweets());

		return tweetMapper.entitiesToDtos(userTweets);
	}

	@Override
	public UserResponseDto updateUsername(String username, UserRequestDto user) {
		Optional<User> findUser = userRepository.findByCredentialsUsername(username);
		
		
		if (findUser.isEmpty()) {
			throw new NotFoundException("User does not exist");
		}
//		
		
//		if(findUser.get().getCredentials().getUsername() != user.getCredentials().getUsername() &&  findUser.get().getCredentials().getPassword() != user.getCredentials().getPassword()) {
//			throw new NotAuthorizedException("Both username and password is not correct");
//
//		}
		
//		if(findUser.get().getCredentials().getUsername() != user.getCredentials().getUsername()) {
//			throw new NotAuthorizedException("Username is not provided so you are not authroized to make this change");		}
//		
//		if(findUser.get().getCredentials().getPassword() != user.getCredentials().getPassword()) {
//			throw new NotAuthorizedException("Password is not provided so you are not authroized to make this change");		}
//		
//		if(findUser.get().isDeleted()) {
//			throw new NotFoundException("User not found");
//		}
//		
		
		findUser.get().getCredentials().setUsername(username);
		return userMapper.entityToDto(findUser.get()); 
			
		
	}
	

	@Override
	public List<UserResponseDto> getFollowing(String user) {
		Optional<User> userToFollow = userRepository.findByCredentialsUsername(user);
		if (userToFollow.isEmpty() || userToFollow.get().isDeleted()) {
			throw new NotFoundException("User does not exist");
		}

		return userMapper.entitiesToDtos(userToFollow.get().getFollowing());
	}

	@Override
	public List<UserResponseDto> getFollowers(String user) {
		Optional<User> userFollowingList = userRepository.findByCredentialsUsername(user);
		if (userFollowingList.isEmpty() || userFollowingList.get().isDeleted()) {
			throw new NotFoundException("User does not exist");
		}

		return userMapper.entitiesToDtos(userFollowingList.get().getFollowers());
	}

	@Override
	public void followUser(String username, CredentialsDto user) {

		Optional<User> foundUser = userRepository.findByCredentialsUsername(username);
		Optional<User> givenUser = userRepository.findByCredentialsUsername(user.getUsername());

		// givenUser needs to follow foundUser

//		if(foundUser.isEmpty() || foundUser.get().isDeleted() || givenUser.isEmpty() || givenUser.get().getCredentials().getPassword() == null || givenUser.get().isDeleted() || givenUser.get().getFollowing().contains(foundUser.get()) || foundUser.get().getFollowers().contains(givenUser.get())) {
//			throw new BadRequestException("nah");
//		}
		
		if(foundUser.isEmpty() || foundUser.get().isDeleted() || givenUser.isEmpty() || givenUser.get().isDeleted() || givenUser.get().getFollowing().contains(foundUser.get()) || foundUser.get().getFollowers().contains(givenUser.get())) {
			throw new BadRequestException("nah");
		}
		
		if(givenUser.get().getCredentials().getPassword() == null) {
			throw new NotAuthorizedException("Not authorized");
		}

//		givenUser.get().getFollowing().add(foundUser.get());
//		userRepository.saveAndFlush(givenUser.get());
		
//		foundUser.get().getFollowing().add(givenUser.get());
//		userRepository.saveAndFlush(foundUser.get());
	}

	@Override
	public void unfollowUser(String username, CredentialsDto user) {
		Optional<User> foundUser = userRepository.findByCredentialsUsername(username);
		Optional<User> givenUser = userRepository.findByCredentialsUsername(user.getUsername());

		// givenUser needs to unfollow foundUser

		if(foundUser.isEmpty() || foundUser.get().isDeleted() || givenUser.isEmpty() || givenUser.get().isDeleted() || !givenUser.get().getFollowing().contains(foundUser.get()) || !foundUser.get().getFollowers().contains(givenUser.get())) {
			throw new BadRequestException("nah");
		}

//		givenUser.get().getFollowing().remove(foundUser.get());
//		userRepository.saveAndFlush(givenUser.get());
		
//		foundUser.get().getFollowing().remove(givenUser.get());
//		userRepository.saveAndFlush(foundUser.get());
		
	}

	@Override
	public UserResponseDto deleteUser(String username) {
		Optional<User> userToDelete = userRepository.findByCredentialsUsername(username);
		if (userToDelete.isEmpty() || userToDelete.get().isDeleted()) {
			throw new NotFoundException("User does not exist");
		}
		
		userToDelete.get().setDeleted(true);
    	userRepository.saveAndFlush(userToDelete.get());
    	return userMapper.entityToDto(userToDelete.get());
	}
}
