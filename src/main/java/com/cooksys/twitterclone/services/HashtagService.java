package com.cooksys.twitterclone.services;

import com.cooksys.twitterclone.dtos.HashtagDto;
import com.cooksys.twitterclone.entities.Hashtag;

import java.util.List;

public interface HashtagService {

    List<HashtagDto> getAllHashTags();
}
