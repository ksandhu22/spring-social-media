package com.cooksys.twitterclone.services;

import java.util.List;

import com.cooksys.twitterclone.dtos.CredentialsDto;
import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.dtos.UserRequestDto;
import com.cooksys.twitterclone.dtos.UserResponseDto;

public interface UserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto addUser(UserRequestDto newUser);

	UserResponseDto updateUsername(String username, UserRequestDto user);

	List<TweetResponseDto> getTweets(String username);

	UserResponseDto getUser(String username);

	List<UserResponseDto> getFollowing(String username);

	List<UserResponseDto> getFollowers(String username);

	void followUser(String username, CredentialsDto user);

	void unfollowUser(String username, CredentialsDto user);

//	boolean validateUsername();
}
