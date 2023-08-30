package com.cooksys.twitterclone.mappers;

import com.cooksys.twitterclone.dtos.HashtagDto;
import com.cooksys.twitterclone.entities.Hashtag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HashtagMapper {
    List<HashtagDto> entitiesToDtos(List<Hashtag> all);
}
