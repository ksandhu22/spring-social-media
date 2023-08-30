package com.cooksys.twitterclone.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.twitterclone.dtos.HashtagDto;
import com.cooksys.twitterclone.entities.Hashtag;

@Mapper(componentModel = "spring")
public interface HashtagMapper {

	List<HashtagDto> entitiesToDtos(List<Hashtag> all);
}
