package com.cooksys.twitterclone.services;

import java.util.List;

import com.cooksys.twitterclone.dtos.HashtagDto;
import com.cooksys.twitterclone.dtos.TweetResponseDto;

public interface HashtagService {

	List<HashtagDto> getAllHashtags();

    List<TweetResponseDto> getTweetsByLabel(String label);
}
