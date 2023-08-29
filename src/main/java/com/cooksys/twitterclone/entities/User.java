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
@Table(name="TwitterUser")
public class User {

    @JoinTable(
            name = "user_likes",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )

    // User ID used as a PK and FK??? Ask about follower/following id
    
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Credentials credentials;

    @Embedded
    private Profile profile;

    @OneToMany(mappedBy = "user")
    private List<Tweet> tweets;
    
    
    
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