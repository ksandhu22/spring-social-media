package com.cooksys.twitterclone.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TweetMapper {
}
