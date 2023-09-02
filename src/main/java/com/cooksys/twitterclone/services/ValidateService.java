package com.cooksys.twitterclone.services;

public interface ValidateService {

	boolean availableUsername(String username);

	boolean usernameExists(String username);

	boolean hashtagExists(String label);

	

}
