package com.cooksys.twitterclone.services.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.dtos.HashtagDto;
import com.cooksys.twitterclone.mappers.HashtagMapper;
import com.cooksys.twitterclone.repositories.HashtagRepository;
import com.cooksys.twitterclone.services.HashtagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {
	
	private final HashtagRepository hashtagRepository;
    private final HashtagMapper hashtagMapper;

	@Override
	public List<HashtagDto> getAllHashtags() {
		return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
	}

}
