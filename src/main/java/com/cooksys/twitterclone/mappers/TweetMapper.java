package com.cooksys.twitterclone.mappers;

import com.cooksys.twitterclone.dtos.TweetRequestDto;
import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.entities.Tweet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})



public interface TweetMapper {
    List<TweetResponseDto> entitiesToDtos(List<Tweet> all);


	Tweet dtoToEntity(TweetResponseDto newTweet);

    TweetResponseDto entityToDto(Tweet foundTweet);

}
