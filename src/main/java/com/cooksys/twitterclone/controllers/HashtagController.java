package com.cooksys.twitterclone.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.twitterclone.dtos.HashtagDto;
import com.cooksys.twitterclone.services.HashtagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hashtags")
public class HashtagController {
	
	private final HashtagService hashtagService;
	
	@GetMapping
	 public List<HashtagDto> getAllHashtags() {
		 return hashtagService.getAllHashtags();
	 }
}
