package com.cooksys.twitterclone.mappers;

import com.cooksys.twitterclone.dtos.CredentialsDto;
import com.cooksys.twitterclone.dtos.UserResponseDto;
import com.cooksys.twitterclone.entities.Credentials;
import com.cooksys.twitterclone.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {
    CredentialsDto entityToDto(Credentials credentials);
    Credentials dtoToEntity(CredentialsDto credentialsDto);
	
}
