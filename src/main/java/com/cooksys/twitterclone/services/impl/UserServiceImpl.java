package com.cooksys.twitterclone.services.impl;

import com.cooksys.twitterclone.dtos.UserResponseDto;
import com.cooksys.twitterclone.entities.User;
import com.cooksys.twitterclone.mappers.UserMapper;
import com.cooksys.twitterclone.repositories.UserRepository;
import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.services.UserService;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public List<UserResponseDto> getAllUsers() {

        // call to repo and find all users and store in variable
        // now create new empty list for non deleted users
        // loop through original list and check if user is deleted or not
        // if a user is not deleted, add it to the new list
        // return that new list

//        List<User> allUserEntities = userRepository.findAll();
//
//        List<User> allActiveUsers = new ArrayList<>();
//
//        for(User currentUser : allUserEntities) {
//            if(!currentUser.isDeleted()) {
//                allActiveUsers.add(currentUser);
//            }
//        }
//
//        return userMapper.entitiesToDtos(allActiveUsers);


         return userMapper.entitiesToDtos(userRepository.findAll());
    }
}
