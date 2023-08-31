package com.cooksys.twitterclone.services.impl;

import com.cooksys.twitterclone.dtos.UserRequestDto;
import com.cooksys.twitterclone.dtos.UserResponseDto;
import com.cooksys.twitterclone.entities.User;
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
    
    
    
    
    
    
}
