package com.cooksys.twitterclone.mappers;

import com.cooksys.twitterclone.dtos.UserRequestDto;
import com.cooksys.twitterclone.dtos.UserResponseDto;
import com.cooksys.twitterclone.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProfileMapper.class, CredentialsMapper.class})
public interface UserMapper {

        @Mapping(target = "username", source = "credentials.username")
        UserResponseDto entityToDto(User user);

        User requestDtoToEntity (UserRequestDto userRequestDto);

        List<UserResponseDto> entitiesToDtos(List<User> all);
        List<User> dtosToEntities(List<UserResponseDto> allUserResponseDtos);

        User dtoToEntity(UserResponseDto newUser);
}
