package com.cooksys.twitterclone.controllers;

import com.cooksys.twitterclone.dtos.TweetResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cooksys.twitterclone.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {
	
	 private final TweetService tweetService;

	 @GetMapping
	 public List<TweetResponseDto> getAllTweets(){
		 return tweetService.getAllTweets();
	 }
}
