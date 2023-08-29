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
public class Tweet {

	
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long id;
    
    private Long author;
    
    @CreationTimestamp
    private Timestamp posted;
    private Boolean deleted;
    private String content;
    private Long inReplyTo;
    private Long repostOf;   
    
    @ManyToMany
    @JoinTable(
    		name = "user_mentions",
    		joinColumns = @JoinColumn(name = "tweet_id"),
    		inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
    
    @ManyToMany(mappedBy = "tweets")
    private List<Hashtag> hashtags;
    
    
}
