package com.cooksys.twitterclone.services;

import com.cooksys.twitterclone.dtos.UserResponseDto;
import com.cooksys.twitterclone.entities.User;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();
}
