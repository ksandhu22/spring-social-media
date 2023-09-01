package com.cooksys.twitterclone.services.impl;

import com.cooksys.twitterclone.dtos.CredentialsDto;
import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.dtos.UserRequestDto;
import com.cooksys.twitterclone.dtos.UserResponseDto;
import com.cooksys.twitterclone.entities.Tweet;
import com.cooksys.twitterclone.entities.User;
import com.cooksys.twitterclone.exceptions.NotAuthorizedException;
import com.cooksys.twitterclone.exceptions.NotFoundException;
import com.cooksys.twitterclone.mappers.CredentialsMapper;
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
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CredentialsMapper credentialsMapper;
    private final TweetMapper tweetMapper;
    
    @Override
    public List<UserResponseDto> getAllUsers() {

         return userMapper.entitiesToDtos(userRepository.findAll().stream().filter(user -> !user.isDeleted()).toList());
    }

    @Override
    public UserResponseDto addUser(UserRequestDto newUser) {

//        {
    //            "credentials": {
    //            "username": "{{firstUser}}",
    //                    "password": "{{password}}"
    //        },
    //            "profile": {
    //            "email": "{{firstUserEmail}}",
    //                    "firstName": "{{firstUserFirstName}}",
    //                    "lastName": "{{firstUserLastName}}",
    //                    "phone": "{{firstUserPhone}}"
    //        }
//        }

        User addedUser = userMapper.requestDtoToEntity(newUser);
//        Optional<User> foundUser = userRepository.findById(addedUser.getId());
//
//        if(foundUser.get().isDeleted()) {
//
//        }

        // gets the username addedUser.getCredentials().getUsername();
        // if this username already exists, and the deleted field is true, change the deleted field to false (reactivate the account)
        // if the username already exists, and the deleted field is false, throw error
        // if required fields are empty (null?) throw error
        // if it looks good, add it

        userRepository.save(addedUser);
        return userMapper.entityToDto(addedUser);
    }



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
	public List<UserResponseDto> getUser(String username) {
    	Optional<User> findUser = userRepository.findByCredentials(username);
		if(findUser.isEmpty()){
			throw new NotFoundException("User is not found");
		
		}
		
		User user = findUser.get();
		
		//Return user not list 
		return userMapper.entityToDto(user);
	}

	@Override
	public List<TweetResponseDto> getTweets(String username) {
		Optional<User> findUser = userRepository.findByCredentials(username);
		if(findUser.isEmpty()){
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

	
    
    
    
    
    
    
}
