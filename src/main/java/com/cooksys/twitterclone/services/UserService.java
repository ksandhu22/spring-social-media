package com.cooksys.twitterclone.services;

import java.util.List;

import com.cooksys.twitterclone.dtos.CredentialsDto;
import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.dtos.UserRequestDto;
import com.cooksys.twitterclone.dtos.UserResponseDto;

public interface UserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto addUser(UserRequestDto newUser);

	UserResponseDto updateUsername(String username, CredentialsDto credentials);

	List<TweetResponseDto> getTweets(String username);

	UserResponseDto getUser(String username);

//	boolean validateUsername();
}
