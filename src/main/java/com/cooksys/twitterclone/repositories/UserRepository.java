package com.cooksys.twitterclone.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cooksys.twitterclone.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByCredentialsUsername(String username);



	@Query(value = "SELECT * FROM user_table WHERE username=?1", nativeQuery = true)
	User findByUsername(String username);

}
