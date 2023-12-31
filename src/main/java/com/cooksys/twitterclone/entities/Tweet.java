package com.cooksys.twitterclone.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Tweet {

	
    @Id
    @GeneratedValue
    private Long id;
    
    @CreationTimestamp
    private Timestamp posted;
    
    private boolean deleted=false;

    @Column(nullable = false)
    private String content;
    
    @ManyToMany
    @JoinTable(
    		name = "user_mentions",
    		joinColumns = @JoinColumn(name = "tweet_id"),
    		inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> mentionedUsers;
    
    @ManyToMany
    @JoinTable(
    		name = "tweet_hashtags",
    		joinColumns = @JoinColumn(name = "tweet_id"),
    		inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
    private List<Hashtag> hashtags;
    
    @ManyToOne
    private User author;
    
    
    //First Tweet Self-Reference
    @ManyToOne
    private Tweet inReplyTo;

    @OneToMany(mappedBy="inReplyTo")
    private List<Tweet> replies = new ArrayList<Tweet>();
    
    
    //Second Tweet Self-Reference
    @ManyToOne
    private Tweet repostOf;

    @OneToMany(mappedBy="repostOf")
    private List<Tweet> reposts = new ArrayList<Tweet>();
    
    @ManyToMany(mappedBy = "likedTweets")
    private List<User> likedByUsers = new ArrayList<>();
    
}
