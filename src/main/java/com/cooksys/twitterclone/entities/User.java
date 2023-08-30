package com.cooksys.twitterclone.entities;

import java.sql.Timestamp;
import java.util.List;


import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="UserTable")
public class User {

    
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long id;

    @Embedded
    private Credentials credentials;

    @Embedded
    private Profile profile;
    
    @OneToMany(mappedBy = "user")
    private List<Tweet> tweet;
    
    
    
    @ManyToMany
    @JoinTable(
    		name = "user_likes",
    		joinColumns = @JoinColumn(name = "user_id"),
    		inverseJoinColumns = @JoinColumn(name = "tweet_id"))
    private List<Tweet> tweets;
    
    @ManyToMany
    @JoinTable(
    		name = "followers_following",
    		joinColumns = @JoinColumn(name = "follower_id"),
    		inverseJoinColumns = @JoinColumn(name = "following_id"))
    private List<User> users;
    		
    
    @Embeddable
    public class Credentials {
        private String username;
        private String password;

    }

    @Embeddable
    public class Profile {
        @CreationTimestamp
        private Timestamp joined;
        private Boolean deleted;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
    }
}