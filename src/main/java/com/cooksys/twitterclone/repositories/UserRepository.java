package com.cooksys.twitterclone.repositories;


import com.cooksys.twitterclone.dtos.CredentialsDto;
import com.cooksys.twitterclone.dtos.UserResponseDto;
import com.cooksys.twitterclone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByCredentials(String username);

	//Why doesn't this work?
	//User findByUsername(String username);

}
